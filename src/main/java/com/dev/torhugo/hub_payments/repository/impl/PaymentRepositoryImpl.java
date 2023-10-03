package com.dev.torhugo.hub_payments.repository.impl;

import com.dev.torhugo.hub_payments.lib.data.database.DatabaseService;
import com.dev.torhugo.hub_payments.lib.data.domain.PaymentModel;
import com.dev.torhugo.hub_payments.lib.data.domain.StoreModel;
import com.dev.torhugo.hub_payments.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
@PropertySource("classpath:query/payment.properties")
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    @Value("${SPI.PAYMENT}")
    private String queryInsertPayment;

    @Value("${SPS.PAYMENT.WHERE.STORE_ID.AND.CUSTOMER_ID.AND.VALUE.AND.EXTERNAL_REFERENCE}")
    private String queryValidatingExistsPayment;

    @Value("${SPS.PAYMENT.WHERE.PAYMENT_ID}")
    private String queryRetrievePaymentById;

    private final DatabaseService databaseService;
    @Override
    public void insert(final PaymentModel paymentModel) {
        databaseService.persist(queryInsertPayment, paymentModel);
    }

    @Override
    public StoreModel validatingExistsPayment(final Long storeId,
                                              final String customer,
                                              final BigDecimal value,
                                              final String externalReference) {
        return databaseService.retrieve(queryValidatingExistsPayment,
                buildParam(storeId, customer, value, externalReference),
                BeanPropertyRowMapper.newInstance(StoreModel.class))
                .orElse(null);
    }

    @Override
    public PaymentModel retrieveById(final String paymentId) {
        return databaseService.retrieve(queryRetrievePaymentById,
                buildParam(paymentId),
                BeanPropertyRowMapper.newInstance(PaymentModel.class))
                .orElse(null);
    }

    private MapSqlParameterSource buildParam(final String paymentId) {
        return new MapSqlParameterSource("paymentId", paymentId);
    }

    private MapSqlParameterSource buildParam(final Long storeId,
                                             final String customer,
                                             final BigDecimal value,
                                             final String externalReference){
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("storeId", storeId);
        parameter.addValue("customer", customer);
        parameter.addValue("value", value);
        parameter.addValue("externalReference", externalReference);
        return parameter;
    }
}
