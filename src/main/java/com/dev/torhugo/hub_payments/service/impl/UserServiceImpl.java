package com.dev.torhugo.hub_payments.service.impl;

import com.dev.torhugo.hub_payments.lib.data.domain.CustomerModel;
import com.dev.torhugo.hub_payments.lib.data.domain.UserModel;
import com.dev.torhugo.hub_payments.lib.data.dto.CustomerRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.CustomerResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.UserRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.UserResponseDTO;
import com.dev.torhugo.hub_payments.mapper.CustomerMapper;
import com.dev.torhugo.hub_payments.mapper.UserMapper;
import com.dev.torhugo.hub_payments.repository.CustomerRepository;
import com.dev.torhugo.hub_payments.repository.UserRepository;
import com.dev.torhugo.hub_payments.service.CustomerService;
import com.dev.torhugo.hub_payments.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final CustomerService customerService;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final UserMapper userMapper;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    public UserResponseDTO register(final UserRequestDTO user) {
        log.info("[1] - Validating exists user to database. Email: [{}].", user.email());
        final UserModel retrieveUser = retrieveUserByEmail(user.email());
        if (Objects.nonNull(retrieveUser)){
            log.info("[2] - User exists. Return to response.");
            log.info("[3] - Retrieve customer in the database.");
            final CustomerModel customerModel = customerRepository.retrieveByUserId(retrieveUser.getUserId());
            log.info("[4] - Mapping to response.");
            return userMapper.modelToResponse(retrieveUser, retrieveUser.getUserId(), mappingCustomerFromCustomer(customerModel));
        }
        log.info("[2] - Mapping to userModel.");
        final UserModel userModel = userMapper.requestToModel(user);
        log.info("[3] - Saving new user.");
        final Long userId = savedUser(userModel);
        log.info("[4] - Register to user in payment.");
        final CustomerResponseDTO customerResponse =
                customerService.registerCustomer(mappingCustomerFromUser(userModel, userId));
        log.info("[5] - Mapping to response.");
        return userMapper.modelToResponse(userModel, userId, customerResponse);
    }

    private Long savedUser(final UserModel userModel){
        userRepository.save(userModel);
        return retrieveUserByEmail(userModel.getEmail()).getUserId();
    }

    private UserModel retrieveUserByEmail(final String email){
        return userRepository.retrieveByEmail(email);
    }

    private CustomerRequestDTO mappingCustomerFromUser(final UserModel userModel, final Long userId){
        return customerMapper.userToRequest(userModel, userId);
    }

    private CustomerResponseDTO mappingCustomerFromCustomer(final CustomerModel customerModel){
        return customerMapper.modelToResponse(customerModel, customerModel.getCustomerId());
    }
}
