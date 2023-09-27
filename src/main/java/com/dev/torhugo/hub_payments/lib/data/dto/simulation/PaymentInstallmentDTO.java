package com.dev.torhugo.hub_payments.lib.data.dto.simulation;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record PaymentInstallmentDTO(
        BigDecimal value,
        Integer number,
        LocalDate dateDue
) {
}
