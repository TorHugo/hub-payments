package com.dev.torhugo.hub_payments.lib.data.dto.tokenize;

import lombok.Builder;

@Builder
public record TokenizeResponseDTO(
        String creditCardNumber,
        String creditCardBrand,
        String creditCardToken
) {
}
