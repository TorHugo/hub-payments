package com.dev.torhugo.hub_payments.repository.impl;

import com.dev.torhugo.hub_payments.lib.data.database.DatabaseService;
import com.dev.torhugo.hub_payments.lib.data.domain.RefundModel;
import com.dev.torhugo.hub_payments.repository.RefundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:query/refund.properties")
@RequiredArgsConstructor
public class RefundRepositoryImpl implements RefundRepository {

    private final DatabaseService databaseService;

    @Value("${SPI.REFUND}")
    private String queryInsertRefund;

    @Value("${SPU.REFUND.SET.STATUS.WHERE.PAYMENT_ID}")
    private String queryUpdateRefunds;

    @Override
    public void insert(final RefundModel refundModel) {
        databaseService.persist(queryInsertRefund, refundModel);
    }

    @Override
    public void updateRefund(final String paymentId,
                             final String status) {
        databaseService.persist(queryUpdateRefunds, buildParam(paymentId, status));
    }

    private MapSqlParameterSource buildParam(final String paymentId,
                                             final String status) {
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("paymentId", paymentId);
        parameter.addValue("status", status);
        return parameter;
    }
}
