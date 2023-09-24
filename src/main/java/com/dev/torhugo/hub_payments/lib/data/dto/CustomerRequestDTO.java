package com.dev.torhugo.hub_payments.lib.data.dto;

import lombok.Builder;

@Builder
public record CustomerRequestDTO(
        Long userId,
        String cpfOrCnpj,
        String fullName,
        String email
) {
}
