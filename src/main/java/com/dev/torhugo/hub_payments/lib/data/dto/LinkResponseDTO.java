package com.dev.torhugo.hub_payments.lib.data.dto;

import lombok.Builder;

@Builder
public record LinkResponseDTO(
        String href,
        String method
) {
}
