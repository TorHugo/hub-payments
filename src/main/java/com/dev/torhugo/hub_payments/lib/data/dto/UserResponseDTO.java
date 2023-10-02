package com.dev.torhugo.hub_payments.lib.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record UserResponseDTO(
        @JsonProperty("user_id")
        Long userId,
        String email,
        @JsonProperty("store_id")
        Long storeId,
        @JsonProperty("cpf_or_cnpj")
        String cpfOrCnpj,
        @JsonProperty("first_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName,
        String phone,
        @JsonProperty("costumer_response")
        CustomerResponseDTO customerResponse
) {
}
