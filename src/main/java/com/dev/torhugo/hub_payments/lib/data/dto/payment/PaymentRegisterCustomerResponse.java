package com.dev.torhugo.hub_payments.lib.data.dto.payment;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PaymentRegisterCustomerResponse(
        String object,
        String id,
        LocalDate dateCreated,
        String name,
        String email,
        String country
) {
}
