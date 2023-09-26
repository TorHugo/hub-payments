package com.dev.torhugo.hub_payments.lib.data.dto;

public record CreditCardHolderInfoDTO(
        String name,
        String email,
        String cpfCnpj,
        String postalCode,
        String addressNumber,
        String phone
) {
}
