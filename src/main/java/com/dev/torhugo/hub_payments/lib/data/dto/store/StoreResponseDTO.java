package com.dev.torhugo.hub_payments.lib.data.dto.store;

import com.dev.torhugo.hub_payments.lib.data.dto.LinkResponseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record StoreResponseDTO(
        @JsonProperty("store_id")
        Long storeId,
        @JsonProperty("cpf_or_cnpj")
        String cpfOrCnpj,
        String email,
        @JsonProperty("_link")
        LinkResponseDTO link
) {
}
