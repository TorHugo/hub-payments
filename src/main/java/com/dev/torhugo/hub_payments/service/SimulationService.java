package com.dev.torhugo.hub_payments.service;

import com.dev.torhugo.hub_payments.lib.data.dto.simulation.SimulationRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.simulation.SimulationResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.simulation.ValueDistributionDTO;

import java.math.BigDecimal;

public interface SimulationService {

    /**
     * Returns an installment simulation to determine the client's monthly payments.
     *
     * @param simulation entry.
     * @return {@link SimulationResponseDTO}
     */
    SimulationResponseDTO simulation(final SimulationRequestDTO simulation);


    /**
     * This method is responsible for distributing the values, <br>
     * between the first installment and the subsequent installments.
     *
     * @param value            the value
     * @param installmentCount the installment count
     * @return the value distribution dto
     */
    ValueDistributionDTO calculatesValueDistribution(final BigDecimal value,
                                                     final Integer installmentCount);

}
