package com.dev.torhugo.hub_payments.service.impl;

import com.dev.torhugo.hub_payments.client.CustomerClient;
import com.dev.torhugo.hub_payments.lib.data.domain.CustomerModel;
import com.dev.torhugo.hub_payments.lib.data.dto.CustomerRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.CustomerResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentRegisterCustomerRequest;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentRegisterCustomerResponse;
import com.dev.torhugo.hub_payments.mapper.CustomerMapper;
import com.dev.torhugo.hub_payments.repository.CustomerRepository;
import com.dev.torhugo.hub_payments.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerClient customerClient;
    private final CustomerMapper customerMapper;

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
