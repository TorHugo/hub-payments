package com.dev.torhugo.hub_payments.resource;

import com.dev.torhugo.hub_payments.lib.data.dto.simulation.SimulationRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.simulation.SimulationResponseDTO;
import com.dev.torhugo.hub_payments.service.impl.factory.SimulationFactory;
import com.dev.torhugo.hub_payments.util.HubResource;
import com.dev.torhugo.hub_payments.util.HubResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/simulation")
@RequiredArgsConstructor
public class InstallmentSimulationController implements HubResource {

    private final SimulationFactory simulationFactory;

    @PostMapping
    public ResponseEntity<HubResponse<SimulationResponseDTO>> simulation (
            @RequestBody final SimulationRequestDTO simulation
    ){
        return returnSuccess(simulationFactory.simulation(simulation));
    }
}
