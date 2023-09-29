package com.dev.torhugo.hub_payments.repository.impl;

import com.dev.torhugo.hub_payments.lib.data.database.DatabaseService;
import com.dev.torhugo.hub_payments.lib.data.domain.PaymentModel;
import com.dev.torhugo.hub_payments.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:query/payment.properties")
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    @Value("${SPI.PAYMENT}")
    private String queryInsertPayment;

    private final DatabaseService databaseService;
    @Override
    public void insert(final PaymentModel paymentModel) {
        databaseService.persist(queryInsertPayment, paymentModel);
    }
}
