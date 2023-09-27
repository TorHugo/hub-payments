package com.dev.torhugo.hub_payments.lib.data.dto.payment;




import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record PaymentRequest(
        String costumer,
        @JsonProperty("billing-type")
        String billingType,
        BigDecimal value,
        @JsonProperty("due-date")
        LocalDate dueDate,
        String description,
        @JsonProperty("external-reference")
        String externalReference

) {
}
