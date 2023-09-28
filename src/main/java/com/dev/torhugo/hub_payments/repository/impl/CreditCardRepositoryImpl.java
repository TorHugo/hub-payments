package com.dev.torhugo.hub_payments.repository.impl;

import com.dev.torhugo.hub_payments.lib.data.database.DatabaseService;
import com.dev.torhugo.hub_payments.lib.data.domain.CreditCardModel;
import com.dev.torhugo.hub_payments.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@PropertySource("classpath:query/credit_card.properties")
@RequiredArgsConstructor
public class CreditCardRepositoryImpl implements CreditCardRepository {
    private final DatabaseService databaseService;

    @Value("${SPI.CREDIT_CARD}")
    private String queryInsertCreditCard;
    @Value("${SPS.CREDIT_CARD.WHERE.TOKEN}")
    private String queryRetrieveByToken;
    @Value("${SPS.CREDIT_CARD.WHERE.ID}")
    private String queryRetrieveById;

    @Override
    public void save(final CreditCardModel creditCardModel) {
        log.info("[-] - save().");
        databaseService.persist(queryInsertCreditCard, creditCardModel);
    }

    @Override
    public CreditCardModel retrieveByToken(final String token) {
        log.info("[-] - retrieveByToken().");
        return databaseService.retrieve(queryRetrieveByToken,
                buildParam(token),
                BeanPropertyRowMapper.newInstance(CreditCardModel.class))
                .orElse(null);
    }

    @Override
    public CreditCardModel retrieveById(final Long creditCardId) {
        return databaseService.retrieve(queryRetrieveById,
                        buildParam(creditCardId),
                        BeanPropertyRowMapper.newInstance(CreditCardModel.class))
                .orElse(null);
    }

    private MapSqlParameterSource buildParam(final String token) {
        return new MapSqlParameterSource("token", token);
    }
    private MapSqlParameterSource buildParam(final Long creditCardId) {
        return new MapSqlParameterSource("creditCardId", creditCardId);
    }
}
