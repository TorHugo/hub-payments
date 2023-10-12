package com.dev.torhugo.hub_payments.service.impl.factory;

import com.dev.torhugo.hub_payments.lib.data.dto.simulation.PaymentInstallmentDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.simulation.SimulationRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.simulation.SimulationResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.simulation.ValueDistributionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.dev.torhugo.hub_payments.service.SimulationService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public abstract class InstallmentHigherServiceImpl implements SimulationService {

    @Override
    public SimulationResponseDTO simulation(final SimulationRequestDTO simulation) {
        return SimulationResponseDTO.builder().build();
    }

    @Override
    public ValueDistributionDTO calculatesValueDistribution(final BigDecimal value,
                                                            final Integer installmentCount){
        final BigDecimal valueOther = value.divide(BigDecimal.valueOf(installmentCount), 2, RoundingMode.FLOOR);
        final BigDecimal restValue = value.subtract(valueOther.multiply(BigDecimal.valueOf(installmentCount)));
        final BigDecimal firstValue = valueOther.add(restValue);

        return ValueDistributionDTO.builder()
                .value(firstValue)
                .otherValue(valueOther)
                .build();
    }

    public SimulationResponseDTO mappingToResponse(final SimulationRequestDTO simulation,
                                                    final List<PaymentInstallmentDTO> lsPaymentInstallment) {
        return SimulationResponseDTO.builder()
                .totalValue(simulation.totalValue())
                .lsPaymentInstallment(lsPaymentInstallment)
                .build();
    }

    public PaymentInstallmentDTO mappingLastPaymentInstallment(final SimulationRequestDTO simulation,
                                                                final Integer idx,
                                                                final ValueDistributionDTO resultDistribution) {
        LocalDate dateDue = simulation.dateDue().plusMonths(idx - 1); // Adiciona idx - 1 meses

        return PaymentInstallmentDTO.builder()
                .value(resultDistribution.otherValue())
                .dateDue(dateDue)
                .number(idx)
                .build();
    }

    public PaymentInstallmentDTO mappingFirstPaymentInstallment(final SimulationRequestDTO simulation,
                                                                 final Integer idx,
                                                                 final ValueDistributionDTO resultDistribution) {
        return PaymentInstallmentDTO.builder()
                .value(resultDistribution.value())
                .dateDue(simulation.dateDue())
                .number(idx)
                .build();
    }
}
