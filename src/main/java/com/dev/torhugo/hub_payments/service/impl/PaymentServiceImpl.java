package com.dev.torhugo.hub_payments.service.impl;

import com.dev.torhugo.hub_payments.client.CustomerClient;
import com.dev.torhugo.hub_payments.lib.data.domain.CreditCardModel;
import com.dev.torhugo.hub_payments.lib.data.domain.CustomerModel;
import com.dev.torhugo.hub_payments.lib.data.domain.PaymentModel;
import com.dev.torhugo.hub_payments.lib.data.dto.CustomerResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentReturnDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.tokenize.TokenizeRequestDTO;
import com.dev.torhugo.hub_payments.lib.exception.impl.DataBaseException;
import com.dev.torhugo.hub_payments.mapper.PaymentMapper;
import com.dev.torhugo.hub_payments.repository.CreditCardRepository;
import com.dev.torhugo.hub_payments.repository.CustomerRepository;
import com.dev.torhugo.hub_payments.repository.PaymentRepository;
import com.dev.torhugo.hub_payments.service.CustomerService;
import com.dev.torhugo.hub_payments.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final CreditCardRepository creditCardRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final CustomerClient customerClient;

    @Override
    public PaymentResponseDTO createPayment(final PaymentRequestDTO paymentRequest) {
        log.info("[0] - Validating customer exists. customerId: [{}].", paymentRequest.customer());
        String tokenization = null;
        final CustomerModel retrieveCustomer = retrieveCustomer(paymentRequest.customer());
        if (Objects.isNull(retrieveCustomer))
            throw new DataBaseException("Customer not found!.", paymentRequest.customer());
        log.info("[1] - Retrieve cardToken of customer.");
        final CreditCardModel retrieveCreditCard = retrieveCreditCardById(retrieveCustomer.getCreditCardId());
        if (Objects.isNull(retrieveCreditCard.getCardToken())) {
            log.info("[2] - Performing a new card tokenization.");
            final CustomerResponseDTO customer = customerService.tokenization(mappingTokenization(paymentRequest));
            tokenization = customer.creditCardResponseDTO().tokenCreditCard();
        }
        log.info("[2] - Mapping to request for payment.");
        final PaymentReturnDTO returnRequest = customerClient.payment(mappingToPaymentRequest(paymentRequest, tokenization));
        log.info("[3] - Mapping to response after payment.");
        final PaymentModel paymentModel = mappingPaymentModel(returnRequest, paymentRequest);
        log.info("[4] - Saving new payment in the database.");
        savePayment(paymentModel);
        log.info("[5] - Mapping to response.");
        return mappingToResponse(paymentModel);
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
