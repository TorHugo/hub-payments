package com.dev.torhugo.hub_payments.repository.impl;

import com.dev.torhugo.hub_payments.lib.data.database.DatabaseService;
import com.dev.torhugo.hub_payments.lib.data.domain.UserModel;
import com.dev.torhugo.hub_payments.lib.exception.impl.DataBaseException;
import com.dev.torhugo.hub_payments.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:query/user.properties")
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private final DatabaseService databaseService;

    @Value("${SPS.USER.WHERE.EMAIL}")
    private String queryRetrieveByEmail;

    @Value("${SPS.USER.WHERE.USER_ID}")
    private String queryRetrieveById;

    @Value("${SPI.USER}")
    private String queryInsertUser;

    @Override
    public UserModel retrieveByEmail(final String email) {
        return databaseService.retrieve(queryRetrieveByEmail,
                buildParam(email),
                BeanPropertyRowMapper.newInstance(UserModel.class))
                .orElse(null);
    }

    @Override
    public void save(final UserModel userModel) {
        databaseService.persist(queryInsertUser, userModel);
    }

    @Override
    public UserModel retrieveById(final Long userId) {
        return databaseService.retrieve(queryRetrieveById,
                buildParam(userId),
                BeanPropertyRowMapper.newInstance(UserModel.class))
                .orElseThrow(() -> new DataBaseException("Entity not found!", userId));
    }

    private MapSqlParameterSource buildParam(final String email) {
        return new MapSqlParameterSource("email", email);
    }
    private MapSqlParameterSource buildParam(final Long userId){
        return new MapSqlParameterSource("userId", userId);
    }
}
