package com.dev.torhugo.hub_payments.lib.data.dto.simulation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record SimulationRequestDTO(
    @JsonProperty("total_value")
    BigDecimal totalValue,
    @JsonProperty("installment_count")
    Integer installmentCount,
    @JsonProperty("date_due")
    LocalDate dateDue,
    @JsonProperty("first_installment")
    String firstInstallment
) {
}
