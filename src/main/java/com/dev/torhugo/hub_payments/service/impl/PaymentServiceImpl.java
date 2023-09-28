package com.dev.torhugo.hub_payments.service.impl;

import com.dev.torhugo.hub_payments.lib.data.domain.CreditCardModel;
import com.dev.torhugo.hub_payments.lib.data.domain.CustomerModel;
import com.dev.torhugo.hub_payments.lib.data.dto.CustomerResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentRequest;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentResponse;
import com.dev.torhugo.hub_payments.lib.data.dto.tokenize.TokenizeRequestDTO;
import com.dev.torhugo.hub_payments.lib.exception.impl.DataBaseException;
import com.dev.torhugo.hub_payments.ma.PaymentMapper;
import com.dev.torhugo.hub_payments.repository.CreditCardRepository;
import com.dev.torhugo.hub_payments.repository.CustomerRepository;
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
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponse createPayment(final PaymentRequest paymentRequest) {
        log.info("[0] - Validating customer exists. customerId: [{}].", paymentRequest.customer());
        final CustomerModel retrieveCustomer = retrieveCustomer(paymentRequest.customer());
        if (Objects.isNull(retrieveCustomer))
            throw new DataBaseException("Customer not found!.", paymentRequest.customer());
        log.info("[1] - Retrieve cardToken of customer.");
        final CreditCardModel retrieveCreditCard = retrieveCreditCardById(retrieveCustomer.getCreditCardId());
        if (Objects.isNull(retrieveCreditCard.getCardToken())) {
            log.info("[2] - Performing a new card tokenization.");
            final CustomerResponseDTO tokenization = customerService.tokenization(mappingTokenization(paymentRequest));
        }

        log.info("[2] - Mapping to request for payment.");
        log.info("[3] - Mapping to response after payment.");
        log.info("[4] - Saving new payment in the database.");
        log.info("[5] - Mapping to response.");
        return null;
    }

    private TokenizeRequestDTO mappingTokenization(final PaymentRequest paymentRequest) {
        return paymentMapper.mapperTokenize(paymentRequest);
    }

    private CreditCardModel retrieveCreditCardById(final Long creditCardId) {
        return creditCardRepository.retrieveById(creditCardId);
    }

    private CustomerModel retrieveCustomer(final String customer) {
        return customerRepository.retrieveByCustomerId(customer);
    }
}
