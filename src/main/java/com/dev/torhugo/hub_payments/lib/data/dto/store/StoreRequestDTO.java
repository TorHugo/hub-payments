package com.dev.torhugo.hub_payments.lib.data.dto.store;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record StoreRequestDTO(
        String email,
        String password,
        @JsonProperty("cpf_or_cnpj")
        String cpfOrCnpj
) {
}
