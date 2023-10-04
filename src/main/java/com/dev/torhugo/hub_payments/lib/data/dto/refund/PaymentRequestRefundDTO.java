package com.dev.torhugo.hub_payments.lib.data.dto.refund;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentRequestRefundDTO(
        @NotNull
        @JsonProperty("payment_id")
        String paymentId,
        @Positive
        BigDecimal value,
        @JsonProperty("refund_message")
        @NotNull
        String description
) {
}
