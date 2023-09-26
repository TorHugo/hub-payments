package com.dev.torhugo.hub_payments.service;

import com.dev.torhugo.hub_payments.lib.data.domain.CustomerModel;
import com.dev.torhugo.hub_payments.lib.data.dto.CustomerRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.CustomerResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.tokenize.TokenizeRequestDTO;

public interface CustomerService {

    /**
     * Register customer customer response dto.
     *
     * @param dto the dto
     * @return the customer response dto
     */
    CustomerResponseDTO registerCustomer(final CustomerRequestDTO dto);

    /**
     * Retrieve by user id customer model.
     *
     * @param userId the user id
     * @return the customer model
     */
    CustomerModel retrieveByUserId(final Long userId);

    CustomerResponseDTO tokenization(final TokenizeRequestDTO tokenization);
}
