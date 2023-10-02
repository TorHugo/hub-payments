package com.dev.torhugo.hub_payments.service.impl;

import com.dev.torhugo.hub_payments.lib.data.domain.StoreModel;
import com.dev.torhugo.hub_payments.lib.data.dto.store.StoreRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.store.StoreResponseDTO;
import com.dev.torhugo.hub_payments.lib.exception.impl.DataBaseException;
import com.dev.torhugo.hub_payments.mapper.StoreMapper;
import com.dev.torhugo.hub_payments.repository.StoreRepository;
import com.dev.torhugo.hub_payments.service.StoreService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    @Override
    @Transactional
    public StoreResponseDTO createStore(final StoreRequestDTO store) {
        log.info("[1] - Validating exists store by CpfOrCnpj: [{}].", store.cpfOrCnpj());
        if (retrieveStoreByCpfCnpj(store.cpfOrCnpj()))
            throw new DataBaseException("Store already exists in the database.", store.cpfOrCnpj());
        log.info("[2] - Mapping to new Store.");
        final StoreModel newStore = mappingToModel(store);
        log.info("[3] - Saving store");
        final StoreModel retrieveNewStore = saving(newStore);
        log.info("[4] - Mapping to response.");
        return mappingToResponse(retrieveNewStore);
    }

    @Override
    public StoreResponseDTO retrieveById(final Long storeId) {
        log.info("[1] - Retrieve Store by StoreId: [{}].", storeId);
        final StoreModel storeModel = retrieveStoreById(storeId);
        log.info("[2] - Validating exists Store.");
        if (Objects.isNull(storeModel))
            throw new DataBaseException("Store not found!. StoreId: ", storeId);
        log.info("[3] - Mapping to response.");
        return mappingToResponse(storeModel);
    }

    private StoreModel retrieveStoreById(final Long storeId) {
        return storeRepository.retrieveById(storeId);
    }

    private StoreResponseDTO mappingToResponse(final StoreModel retrieveNewStore) {
        return storeMapper.mapperToResponse(retrieveNewStore);
    }

    private StoreModel saving(final StoreModel newStore) {
        storeRepository.insert(newStore);
        return storeRepository.retrieveByCpfCnpj(newStore.getCpfOrCnpj());
    }

    private StoreModel mappingToModel(final StoreRequestDTO store) {
        return storeMapper.mapperToModel(store);
    }

    private boolean retrieveStoreByCpfCnpj(final String cpfOrCnpj) {
        return Objects.nonNull(storeRepository.retrieveByCpfCnpj(cpfOrCnpj)) ? Boolean.TRUE : Boolean.FALSE;
    }
}
