package com.dev.torhugo.hub_payments.lib.data.dto.payment;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record PaymentReturnDTO(
        String id,
        String customer,
        LocalDate dateCreated,
        BigDecimal value,
        BigDecimal netValue,
        String billingType,
        String status
) {
}
