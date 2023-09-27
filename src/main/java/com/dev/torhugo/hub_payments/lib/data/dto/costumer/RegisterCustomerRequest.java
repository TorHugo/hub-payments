package com.dev.torhugo.hub_payments.lib.data.dto.costumer;

import lombok.Builder;

@Builder
public record RegisterCustomerRequest(
        String name,
        String cpfcnpj,
        String email
) {
}
