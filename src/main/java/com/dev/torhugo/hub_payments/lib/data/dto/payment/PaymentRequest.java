package com.dev.torhugo.hub_payments.lib.data.dto.payment;




import com.dev.torhugo.hub_payments.lib.data.dto.CreditCardDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.CreditCardHolderInfoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record PaymentRequest(
        String customer,
        @JsonProperty("billing-type")
        String billingType,
        BigDecimal value,
        @JsonProperty("due-date")
        LocalDate dueDate,
        String description,
        @JsonProperty("external-reference")
        String externalReference,
        @JsonProperty("credit_card")
        CreditCardDTO creditCard,
        @JsonProperty("credit_card_holder_info")
        CreditCardHolderInfoDTO creditCardHolderInfo
) {
}
