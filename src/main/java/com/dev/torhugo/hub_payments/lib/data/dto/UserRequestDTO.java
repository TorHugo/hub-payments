package com.dev.torhugo.hub_payments.lib.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserRequestDTO(
        String email,
        String password,
        @JsonProperty("cpf_or_cnpj")
        String cpfOrCnpj,
        @JsonProperty("first_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName,
        String phone
) {
}
