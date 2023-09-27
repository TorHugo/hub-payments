package com.dev.torhugo.hub_payments.service.impl.factory;

import com.dev.torhugo.hub_payments.lib.data.dto.simulation.SimulationRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.simulation.SimulationResponseDTO;
import com.dev.torhugo.hub_payments.lib.exception.impl.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimulationFactory {

    private final FirstInstallmentHigherServiceImpl firstInstallmentHigher;
    private final LastInstallmentHigherSerivceImpl lastInstallmentHigherSerivce;

    public SimulationResponseDTO simulation(final SimulationRequestDTO simulation){
        InstallmentHigherServiceImpl installmentHigherService = null;

        switch (simulation.firstInstallment()){
            case "true":
                installmentHigherService = firstInstallmentHigher;
                return installmentHigherService.simulation(simulation);
            case "false":
                installmentHigherService = lastInstallmentHigherSerivce;
                return installmentHigherService.simulation(simulation);
            default:
                throw new ResourceNotFoundException("Value incompatible.");
        }
    }
}
