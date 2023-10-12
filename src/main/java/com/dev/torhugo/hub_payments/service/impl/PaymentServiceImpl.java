package com.dev.torhugo.hub_payments.service.impl;

import com.dev.torhugo.hub_payments.client.CustomerClient;
import com.dev.torhugo.hub_payments.lib.data.domain.*;
import com.dev.torhugo.hub_payments.lib.data.dto.CustomerResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.*;
import com.dev.torhugo.hub_payments.lib.data.dto.refund.PaymentRefundDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.refund.PaymentRequestRefundDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.refund.PaymentResponseRefundDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.tokenize.TokenizeRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.enumerator.MessageEnum;
import com.dev.torhugo.hub_payments.lib.exception.impl.DataBaseException;
import com.dev.torhugo.hub_payments.lib.exception.impl.ResourceNotFoundException;
import com.dev.torhugo.hub_payments.mapper.PaymentMapper;
import com.dev.torhugo.hub_payments.mapper.RefundMapper;
import com.dev.torhugo.hub_payments.repository.*;
import com.dev.torhugo.hub_payments.service.CustomerService;
import com.dev.torhugo.hub_payments.service.PaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.dev.torhugo.hub_payments.lib.data.enumerator.MessageEnum.FOUND_RECURRENCE;
import static com.dev.torhugo.hub_payments.lib.data.enumerator.MessageEnum.NOT_FOUND;
import static com.dev.torhugo.hub_payments.util.ConstraintUtil.STATUS_REFUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl extends DefaultServiceImpl implements PaymentService {

    private final CustomerService customerService;
    private final StoreRepository storeRepository;
    private final CustomerRepository customerRepository;
    private final CreditCardRepository creditCardRepository;
    private final PaymentRepository paymentRepository;
    private final RefundRepository refundRepository;
    private final PaymentMapper paymentMapper;
    private final RefundMapper refundMapper;
    private final CustomerClient customerClient;

    @Override
    @Transactional
    public PaymentResponseDTO createPayment(final PaymentRequestDTO paymentRequest) {
        String tokenization = null;
        log.info("[0] - Validation externalReference.");
        if (Objects.nonNull(retrieveStore(paymentRequest)))
            super.defaultError(FOUND_RECURRENCE, "Payment", paymentRequest.customer(), null, null);
        log.info("[1] - Validating store exists. storeId: [{}].", paymentRequest.storeId());
        if (Objects.isNull(retrieveStore(paymentRequest.storeId())))
            super.defaultError(NOT_FOUND, "Store", paymentRequest.storeId().toString(), "/store", "[POST]");
        log.info("[2] - Validating customer exists. customerId: [{}].", paymentRequest.customer());
        final CustomerModel retrieveCustomer = retrieveCustomer(paymentRequest.customer());
        if (Objects.isNull(retrieveCustomer))
            super.defaultError(NOT_FOUND, "Customer", paymentRequest.customer(), "/user/register", "[POST]");
        log.info("[3] - Retrieve cardToken of customer.");
        final CreditCardModel retrieveCreditCard = retrieveCreditCardById(retrieveCustomer.getCreditCardId());
        if (Objects.isNull(retrieveCreditCard.getCardToken())) {
            log.info("[4] - Performing a new card tokenization.");
            final CustomerResponseDTO customer = customerService.tokenization(mappingTokenization(paymentRequest));
            tokenization = customer.creditCardResponseDTO().tokenCreditCard();
        }
        log.info("[4] - Mapping to request for payment.");
        final PaymentReturnDTO returnRequest = customerClient.payment(mappingToPaymentRequest(paymentRequest, tokenization));
        log.info("[5] - Mapping to response after payment.");
        final PaymentModel paymentModel = mappingPaymentModel(returnRequest, paymentRequest);
        log.info("[6] - Saving new payment in the database.");
        savePayment(paymentModel);
        log.info("[7] - Mapping to response.");
        return mappingToResponse(paymentModel);
    }

    @Override
    public PaymentResponseDTO retrieveById(final String paymentId) {
        log.info("[1] - Retrieve payment by PaymentId: [{}].", paymentId);
        final PaymentModel retrievePayment = retrievePayment(paymentId);
        if (Objects.isNull(retrievePayment))
            super.defaultError(NOT_FOUND, "Payment", paymentId, "/payment", "[POST]");
        log.info("[2] - Mapping to response.");
        return mappingToResponse(retrievePayment);
    }

    @Override
    public PaymentResponseRefundDTO refundPayment(final PaymentRequestRefundDTO refund) {
        log.info("[1] - Retrieve payment in the database.");
        final PaymentModel paymentModel = retrievePayment(refund.paymentId());
        log.info("[2] - Validating refund. PaymentId: [{}].", refund.paymentId());
        validatingRefund(paymentModel);
        log.info("[3] - Processing a refund.");
        final PaymentRefundDTO responseToRefund = customerClient.refund(refund);
        log.info("[2] - Mapping to model.");
        final RefundModel refundModel = mappingToRefund(refund, responseToRefund);
        log.info("[5] - Saving REFUND and update PAYMENT in the database.");
        saveRefund(refundModel, responseToRefund);
        log.info("[6] - Mapping to response.");
        return mappingToResponseRefund(responseToRefund, refund);
    }

    private PaymentResponseRefundDTO mappingToResponseRefund(final PaymentRefundDTO responseToRefund,
                                                             final PaymentRequestRefundDTO refund) {
        return refundMapper.mapperToResponse(refund, responseToRefund);
    }

    private void saveRefund(final RefundModel refundModel, final PaymentRefundDTO responseToRefund) {
        refundRepository.insert(refundModel);

        if (Objects.equals(responseToRefund.status(), STATUS_REFUND)) {
            paymentRepository.updateRefund(refundModel.getPaymentId(), responseToRefund.status());
            refundRepository.updateRefund(refundModel.getPaymentId(), refundModel.getStatus());
        }
    }

    private RefundModel mappingToRefund(final PaymentRequestRefundDTO refund,
                                        final PaymentRefundDTO responseToRefund) {
        return refundMapper.mapperToModel(refund, responseToRefund);
    }

    private void validatingRefund(final PaymentModel paymentModel) {
        if (Objects.isNull(paymentModel))
            super.defaultError(NOT_FOUND, "Payment", null, "/payment", "[POST]");
        if (Objects.equals(paymentModel.getStatus(), STATUS_REFUND))
            throw new ResourceNotFoundException("The refund has already been processed for this purchase!.");
    }

    private PaymentModel retrievePayment(final String paymentId) {
        return paymentRepository.retrieveById(paymentId);
    }

    private StoreModel retrieveStore(final PaymentRequestDTO request) {
        return paymentRepository
                .validatingExistsPayment(request.storeId(), request.customer(), request.value(), request.externalReference());
    }

    private StoreModel retrieveStore(final Long storeId) {
        return storeRepository.retrieveById(storeId);
    }

    private PaymentResponseDTO mappingToResponse(final PaymentModel paymentModel) {
        return paymentMapper.mapperToResponse(paymentModel);
    }

    private void savePayment(final PaymentModel paymentModel) {
        paymentRepository.insert(paymentModel);
    }

    private PaymentModel mappingPaymentModel(final PaymentReturnDTO returnRequest,
                                             final PaymentRequestDTO paymentRequest) {
        return paymentMapper.mapperToModel(returnRequest, paymentRequest);
    }

    private PaymentDTO mappingToPaymentRequest(final PaymentRequestDTO paymentRequest,
                                               final String tokenization) {
        return paymentMapper.mapperToRequest(paymentRequest, tokenization);
    }

    private TokenizeRequestDTO mappingTokenization(final PaymentRequestDTO paymentRequest) {
        return paymentMapper.mapperTokenize(paymentRequest);
    }

    private CreditCardModel retrieveCreditCardById(final Long creditCardId) {
        return creditCardRepository.retrieveById(creditCardId);
    }

    private CustomerModel retrieveCustomer(final String customer) {
        return customerRepository.retrieveByCustomerId(customer);
    }
}
