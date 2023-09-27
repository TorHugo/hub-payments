package com.dev.torhugo.hub_payments.service.impl.factory;

import com.dev.torhugo.hub_payments.lib.data.dto.simulation.PaymentInstallmentDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.simulation.SimulationRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.simulation.SimulationResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.simulation.ValueDistributionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class FirstInstallmentHigherServiceImpl extends InstallmentHigherServiceImpl {

    private static final Integer INITIAL_COUNT = 1;

    @Override
    public SimulationResponseDTO simulation(final SimulationRequestDTO simulation) {
        log.info("[0] - Initial first installment higher.");
        log.info("[1] - Calculate value distribution. totalValue: [{}].", simulation.totalValue());
        final ValueDistributionDTO resultDistribution =
                super.calculatesValueDistribution(simulation.totalValue(), simulation.installmentCount());
        log.info("[2] - Mapping to response.");
        return mappingToResponse(simulation, resultDistribution);
    }

    private SimulationResponseDTO mappingToResponse(final SimulationRequestDTO simulation,
                                                    final ValueDistributionDTO resultDistribution) {
        List<PaymentInstallmentDTO> lsPaymentInstallment = new ArrayList<>();

        IntStream.rangeClosed(INITIAL_COUNT, simulation.installmentCount()).forEach(idx -> {
            if (idx == INITIAL_COUNT) {
                lsPaymentInstallment.add(super.mappingFirstPaymentInstallment(simulation, idx, resultDistribution));
            } else {
                lsPaymentInstallment.add(super.mappingLastPaymentInstallment(simulation, idx, resultDistribution));
            }
        });

        return super.mappingToResponse(simulation, lsPaymentInstallment);
    }

}
