package com.dev.torhugo.hub_payments.lib.data.dto.refund;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record PaymentResponseRefundDTO(
        @JsonProperty("payment_id")
        String paymentId,
        @JsonProperty("status_payment")
        String statusPayment,
        @JsonProperty("total_value")
        BigDecimal totalValue,
        List<ResponseRefundDTO> refunds
) {
}
