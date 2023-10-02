package com.dev.torhugo.hub_payments.service;

import com.dev.torhugo.hub_payments.lib.data.dto.store.StoreRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.store.StoreResponseDTO;

public interface StoreService {

    /**
     * Create Store.
     *
     * @param store the store
     * @return the store response dto
     */
    StoreResponseDTO createStore(final StoreRequestDTO store);
}
