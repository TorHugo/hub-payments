package com.dev.torhugo.hub_payments.repository.impl;

import com.dev.torhugo.hub_payments.lib.data.database.DatabaseService;
import com.dev.torhugo.hub_payments.lib.data.domain.CreditCardModel;
import com.dev.torhugo.hub_payments.lib.data.domain.CustomerModel;
import com.dev.torhugo.hub_payments.lib.data.enumerator.FormPaymentEnum;
import com.dev.torhugo.hub_payments.repository.CustomerRepository;
import com.dev.torhugo.hub_payments.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Map;
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
    @Value("${SPS.CUSTOMER.WHERE.CUSTOMER_ID}")
    private String queryRetrieveCustomerByCustomerId;
    @Value("${SPI.CUSTOMER}")
    private String queryInsertCustomer;
    @Value("${SPU.CUSTOMER.SET.CREDIT_CARD_ID.WHERE.CUSTOMER_ID}")
    private String queryUpdateCustomerIntoCreditCardId;

    private final DatabaseService databaseService;

    @Override
    public CustomerModel retrieveByUserId(final Long userId) {
        log.info("[-] - retrieveByUserId().");
        return databaseService.retrieve(queryRetrieveCustomerByUserId,
                buildParam(userId),
                BeanPropertyRowMapper.newInstance(CustomerModel.class))
                .orElse(null);
    }

    @Override
    public void save(final CustomerModel customerModel) {
        databaseService.persist(queryInsertCustomer, customerModel);
    }

    @Override
    public CustomerModel retrieveByCustomerId(final String customer) {
        return databaseService.retrieve(queryRetrieveCustomerByCustomerId,
                buildParam(customer),
                BeanPropertyRowMapper.newInstance(CustomerModel.class))
                .orElse(null);
    }

    @Override
    public void update(final CustomerModel customerModel,
                       final CreditCardModel creditCardModel,
                       final FormPaymentEnum formPaymentEnum) {
        databaseService.persist(queryUpdateCustomerIntoCreditCardId,
                buildParam(customerModel, creditCardModel, formPaymentEnum));
    }

    private MapSqlParameterSource buildParam(final CustomerModel customerModel, final CreditCardModel creditCardModel, final FormPaymentEnum formPaymentEnum) {
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("customerId", customerModel.getCustomerId());
        parameter.addValue("creditCardId", creditCardModel.getCreditCardId());
        parameter.addValue("formPaymentId", formPaymentEnum.getFormPaymentId());
        return parameter;
    }

    private MapSqlParameterSource buildParam(final Long userId) {
        return new MapSqlParameterSource("userId", userId);
    }
    private MapSqlParameterSource buildParam(final String customer) {
        return new MapSqlParameterSource("customerId", customer);
    }
}
