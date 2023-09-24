package com.dev.torhugo.hub_payments.service;

import com.dev.torhugo.hub_payments.lib.data.dto.CustomerRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.CustomerResponseDTO;

public interface CustomerService {

    CustomerResponseDTO registerCustomer(final CustomerRequestDTO dto);
}
