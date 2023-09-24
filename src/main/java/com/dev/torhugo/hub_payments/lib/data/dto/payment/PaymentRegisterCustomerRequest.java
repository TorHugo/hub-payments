package com.dev.torhugo.hub_payments.lib.data.dto.payment;

import lombok.Builder;

@Builder
public record PaymentRegisterCustomerRequest(
        String name,
        String cpfcnpj,
        String email
) {
}
