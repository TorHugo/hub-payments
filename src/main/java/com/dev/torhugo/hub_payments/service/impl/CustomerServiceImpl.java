package com.dev.torhugo.hub_payments.service.impl;

import com.dev.torhugo.hub_payments.client.CustomerClient;
import com.dev.torhugo.hub_payments.lib.data.domain.CreditCardModel;
import com.dev.torhugo.hub_payments.lib.data.domain.CustomerModel;
import com.dev.torhugo.hub_payments.lib.data.dto.CustomerRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.CustomerResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.tokenize.TokenizeRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentRegisterCustomerRequest;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentRegisterCustomerResponse;
import com.dev.torhugo.hub_payments.lib.data.dto.tokenize.TokenizeResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.enumerator.FormPaymentEnum;
import com.dev.torhugo.hub_payments.lib.exception.impl.DataBaseException;
import com.dev.torhugo.hub_payments.lib.exception.impl.ResourceNotFoundException;
import com.dev.torhugo.hub_payments.mapper.CustomerMapper;
import com.dev.torhugo.hub_payments.repository.CreditCardRepository;
import com.dev.torhugo.hub_payments.repository.CustomerRepository;
import com.dev.torhugo.hub_payments.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerClient customerClient;
    private final CustomerMapper customerMapper;
    private final CreditCardMapper creditCardMapper;
    private final CreditCardRepository creditCardRepository;

    private static final FormPaymentEnum FORM_CREDIT_CARD = FormPaymentEnum.CREDIT_CARD;

    @Override
    @Transactional
    public CustomerResponseDTO registerCustomer(final CustomerRequestDTO customerDTO) {
        log.info("[1] - Validating exists customer. userId: {}.", customerDTO.userId());
        final CustomerModel retrieveCustomer = retrieveCustomer(customerDTO.userId());
        if (Objects.nonNull(retrieveCustomer)){
            log.info("[2] - Customer already exists.");
            return customerMapper.modelToResponse(retrieveCustomer, retrieveCustomer.getCustomerId());
        }
        log.info("[2] - Saving to customer.");
        final PaymentRegisterCustomerResponse savedClient = customerClient.register(mappingToRequest(customerDTO));
        log.info("[3] - Saving in the database.");
        final CustomerModel customerModel = saved(savedClient, customerDTO);
        log.info("[4] - Mapping to response.");
        return mappingToResponse(customerModel);
    }

    @Override
    public CustomerModel retrieveByUserId(final Long userId) {
        log.info("[1] - Retrieve Customer by UserId. [{}]", userId);
        return customerRepository.retrieveByUserId(userId);
    }

    @Override
    public CustomerResponseDTO tokenization(final TokenizeRequestDTO tokenization) {
        log.info("[1] - Validating customer in the database. customerId: [{}].", tokenization.customer());
        final CustomerModel customerModel =
                customerRepository.retrieveByCustomerId(tokenization.customer());
        if (Objects.isNull(customerModel))
            throw new DataBaseException("Entity not found!", tokenization.customer());
        log.info("[1] - Register new tokenization of creditCard.");
        final TokenizeResponseDTO response = customerClient.tokenize(tokenization);
        log.info("[2] - Saved creditCard in the database.");
        return savedCreditCard(response, customerModel);
    }

    private CustomerResponseDTO savedCreditCard(final TokenizeResponseDTO response,
                                                final CustomerModel customerModel) {
        log.info("[-] - savedCreditCard()");
        final CreditCardModel creditCardModel = mappingToCreditCard(response);
        log.info("[-] - Saved creditCardInfo in the database.");
        creditCardRepository.save(creditCardModel);
        log.info("[-] - Retrieve CreditCard by token.");
        final CreditCardModel retrieveByToken =
                creditCardRepository.retrieveByToken(response.creditCardToken());
        log.info("[-] - Update to customer.");
        updateCustomer(customerModel, retrieveByToken, FORM_CREDIT_CARD);
        log.info("[-] - Mapping response.");
        return mappingToResponse(customerModel, retrieveByToken);
    }

    private CustomerResponseDTO mappingToResponse(final CustomerModel customerModel,
                                                  final CreditCardModel creditCardModel) {
        return customerMapper.mappingToResponse(customerModel, creditCardModel);
    }

    private void updateCustomer(final CustomerModel customerModel,
                                final CreditCardModel retrieveCreditCard,
                                final FormPaymentEnum formPaymentEnum) {
        customerRepository.update(customerModel, retrieveCreditCard, formPaymentEnum);
    }

    private CreditCardModel mappingToCreditCard(final TokenizeResponseDTO response) {
        return creditCardMapper.mappingToModel(response);
    }

    private CustomerResponseDTO mappingToResponse(final CustomerModel customerModel) {
        return CustomerResponseDTO.builder()
                .customerId(customerModel.getCustomerId())
                .inActive(customerModel.getInActive())
                .build();
    }

    private CustomerModel saved(final PaymentRegisterCustomerResponse savedClient,
                                final CustomerRequestDTO customerDTO) {
        CustomerModel returned = mappingToModel(savedClient, customerDTO);
        customerRepository.save(returned);
        return returned;
    }

    private CustomerModel mappingToModel(final PaymentRegisterCustomerResponse savedClient,
                                         final CustomerRequestDTO customerDTO) {
        return customerMapper.mappingToModel(savedClient, customerDTO);
    }

    private PaymentRegisterCustomerRequest mappingToRequest(final CustomerRequestDTO dto) {
        return PaymentRegisterCustomerRequest.builder()
                .cpfcnpj(dto.cpfOrCnpj())
                .email(dto.email())
                .name(dto.fullName())
                .build();
    }

    private CustomerModel retrieveCustomer(final Long userId){
        return customerRepository.retrieveByUserId(userId);
    }

}
