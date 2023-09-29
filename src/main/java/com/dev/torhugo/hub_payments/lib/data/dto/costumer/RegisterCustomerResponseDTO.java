package com.dev.torhugo.hub_payments.lib.data.dto.costumer;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record RegisterCustomerResponseDTO(
        String object,
        String id,
        LocalDate dateCreated,
        String name,
        String email,
        String country
) {
}
