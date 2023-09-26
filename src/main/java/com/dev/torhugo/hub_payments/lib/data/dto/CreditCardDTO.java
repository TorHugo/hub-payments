package com.dev.torhugo.hub_payments.lib.data.dto;

import lombok.Builder;

@Builder
public record CreditCardDTO(
        String holderName,
        String number,
        String expiryMonth,
        String expiryYear,
        String ccv
) {
}
