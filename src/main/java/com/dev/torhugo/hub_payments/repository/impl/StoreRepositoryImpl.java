package com.dev.torhugo.hub_payments.repository.impl;

import com.dev.torhugo.hub_payments.lib.data.database.DatabaseService;
import com.dev.torhugo.hub_payments.lib.data.domain.StoreModel;
import com.dev.torhugo.hub_payments.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@PropertySource("classpath:query/store.properties")
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepository {

    private final DatabaseService databaseService;

    @Value("${SPS.STORE.WHERE.CPF_OR_CNPJ}")
    private String queryRetrieveByCpfOrCnpj;
    @Value("${SPS.STORE.WHERE.STORE_ID}")
    private String queryRetrieveById;
    @Value("${SPI.STORE}")
    private String queryPersistStore;

    @Override
    public Optional<StoreModel> retrieveByCpfCnpj(final String cpfOrCnpj) {
        return databaseService.retrieve(queryRetrieveByCpfOrCnpj,
                                        buildParam(cpfOrCnpj),
                                        BeanPropertyRowMapper.newInstance(StoreModel.class));
    }

    @Override
    public void insert(final StoreModel newStore) {
        databaseService.persist(queryPersistStore, newStore);
    }

    @Override
    public StoreModel retrieveById(final Long storeId) {
        return databaseService.retrieve(queryRetrieveById,
                buildParam(storeId),
                BeanPropertyRowMapper.newInstance(StoreModel.class))
                .orElse(null);
    }

    private MapSqlParameterSource buildParam(final String cpfOrCnpj) {
        return new MapSqlParameterSource("cpfOrCnpj", cpfOrCnpj);
    }
    private MapSqlParameterSource buildParam(final Long storeId) {
        return new MapSqlParameterSource("storeId", storeId);
    }
}
