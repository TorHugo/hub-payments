package com.dev.torhugo.hub_payments.service.impl;

import com.dev.torhugo.hub_payments.lib.data.domain.StoreModel;
import com.dev.torhugo.hub_payments.lib.data.dto.store.StoreRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.store.StoreResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.enumerator.MessageEnum;
import com.dev.torhugo.hub_payments.lib.exception.impl.DataBaseException;
import com.dev.torhugo.hub_payments.mapper.StoreMapper;
import com.dev.torhugo.hub_payments.repository.StoreRepository;
import com.dev.torhugo.hub_payments.service.StoreService;
import com.dev.torhugo.hub_payments.util.GenericUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.dev.torhugo.hub_payments.lib.data.enumerator.MessageEnum.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreServiceImpl extends DefaultServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    @Override
    @Transactional
    public StoreResponseDTO createStore(final StoreRequestDTO store) {
        log.info("[1] - Validating exists store by CpfOrCnpj: [{}].", store.cpfOrCnpj());
        if (retrieveStoreByCpfCnpj(store.cpfOrCnpj()))
            super.defaultError(FOUND_RECURRENCE, "Store", store.cpfOrCnpj(), "/store/retrieve/".concat(store.cpfOrCnpj()), "[GET]");
        log.info("[2] - Mapping to new Store.");
        final StoreModel newStore = mappingToModel(store);
        log.info("[3] - Saving store");
        final StoreModel retrieveNewStore = saving(newStore);
        log.info("[4] - Mapping to response.");
        return mappingToResponse(retrieveNewStore);
    }

    @Override
    public StoreResponseDTO retrieveByCpfOrCnpj(final String cpfOrCnpj) {
        log.info("[1] - Retrieve Store by CpfOrCnpj: [{}].", cpfOrCnpj);
        final StoreModel storeModel = retrieveStoreByCpfOrCnpj(cpfOrCnpj);
        log.info("[2] - Validating exists Store.");
        if (Objects.isNull(storeModel))
            super.defaultError(NOT_FOUND, "Store", cpfOrCnpj, "/store", "[POST]");
        log.info("[3] - Mapping to response.");
        return mappingToResponse(storeModel);
    }

    private StoreModel retrieveStoreByCpfOrCnpj(final String cpfOrCnpj) {
        return storeRepository.retrieveByCpfCnpj(cpfOrCnpj);
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
