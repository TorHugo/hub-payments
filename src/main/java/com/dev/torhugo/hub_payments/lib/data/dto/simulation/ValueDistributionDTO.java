package com.dev.torhugo.hub_payments.lib.data.dto.simulation;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ValueDistributionDTO(
        BigDecimal value,
        BigDecimal otherValue
) {
}
