package com.dev.torhugo.hub_payments.repository.impl;

import com.dev.torhugo.hub_payments.lib.data.database.DatabaseService;
import com.dev.torhugo.hub_payments.lib.data.domain.CustomerModel;
import com.dev.torhugo.hub_payments.repository.CustomerRepository;
import com.dev.torhugo.hub_payments.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@PropertySource("classpath:query/customer.properties")
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    @Value("${SPS.CUSTOMER.WHERE.USER_ID}")
    private String queryRetrieveCustomerByUserId;
    @Value("${SPI.CUSTOMER}")
    private String queryInsertCustomer;

    private final DatabaseService databaseService;

    @Override
    public CustomerModel retrieveByUserId(final Long userId) {
        return databaseService.retrieve(queryRetrieveCustomerByUserId,
                buildParam(userId),
                BeanPropertyRowMapper.newInstance(CustomerModel.class))
                .orElse(null);
    }

    @Override
    public void save(final CustomerModel customerModel) {
        databaseService.persist(queryInsertCustomer, customerModel);
    }

    private MapSqlParameterSource buildParam(final Long userId) {
        return new MapSqlParameterSource("userId", userId);
    }
}
