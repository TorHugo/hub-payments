package com.dev.torhugo.hub_payments.lib.data.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record PaymentResponseDTO(
        @JsonProperty("payment_id")
        String paymentId,
        @JsonProperty("date_created")
        LocalDate dateCreated,
        @JsonProperty("customer_id")
        String customerId,
        BigDecimal value,
        @JsonProperty("due_date")
        LocalDate dueDate,
        @JsonProperty("billing_type")
        String billingType,
        String status,
        String href
) {
}
