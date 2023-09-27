package com.dev.torhugo.hub_payments.lib.data.dto.simulation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record SimulationResponseDTO(
        @JsonProperty("total_value")
        BigDecimal totalValue,
        @JsonProperty("ls_payment_installment")
        List<PaymentInstallmentDTO> lsPaymentInstallment
) {
}
