package com.dev.torhugo.hub_payments.lib.data.dto.payment;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record PaymentDTO(
        String customer,
        String billingType,
        BigDecimal value,
        LocalDate dueDate,
        String description,
        String creditCardToken
) {
}
