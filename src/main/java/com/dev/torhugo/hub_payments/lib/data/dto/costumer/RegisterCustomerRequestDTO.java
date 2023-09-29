package com.dev.torhugo.hub_payments.lib.data.dto.costumer;

import lombok.Builder;

@Builder
public record RegisterCustomerRequestDTO(
        String name,
        String cpfcnpj,
        String email
) {
}
