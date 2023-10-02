package com.dev.torhugo.hub_payments.resource;

import com.dev.torhugo.hub_payments.lib.data.dto.store.StoreRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.store.StoreResponseDTO;
import com.dev.torhugo.hub_payments.service.StoreService;
import com.dev.torhugo.hub_payments.util.HubResource;
import com.dev.torhugo.hub_payments.util.HubResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/store")
@RequiredArgsConstructor
public class StoreController implements HubResource {

    private final StoreService storeService;

    @PostMapping
    public ResponseEntity<HubResponse<StoreResponseDTO>> createStore(
            @RequestBody final StoreRequestDTO store
    ){
        return returnSuccess(storeService.createStore(store));
    }

    @GetMapping("/retrieve/{storeId}")
    public ResponseEntity<HubResponse<StoreResponseDTO>> retrieveStore(
            @PathVariable final Long storeId
    ) {
        return returnSuccess(storeService.retrieveById(storeId));
    }

}
