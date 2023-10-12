package com.dev.torhugo.hub_payments.service.impl;

import com.dev.torhugo.hub_payments.lib.data.domain.CustomerModel;
import com.dev.torhugo.hub_payments.lib.data.domain.StoreModel;
import com.dev.torhugo.hub_payments.lib.data.domain.UserModel;
import com.dev.torhugo.hub_payments.lib.data.dto.CustomerRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.CustomerResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.UserRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.UserResponseDTO;
import com.dev.torhugo.hub_payments.lib.exception.impl.DataBaseException;
import com.dev.torhugo.hub_payments.mapper.CustomerMapper;
import com.dev.torhugo.hub_payments.mapper.UserMapper;
import com.dev.torhugo.hub_payments.repository.CustomerRepository;
import com.dev.torhugo.hub_payments.repository.StoreRepository;
import com.dev.torhugo.hub_payments.repository.UserRepository;
import com.dev.torhugo.hub_payments.service.CustomerService;
import com.dev.torhugo.hub_payments.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.dev.torhugo.hub_payments.lib.data.enumerator.MessageEnum.CONTACT_SUPPORT;
import static com.dev.torhugo.hub_payments.lib.data.enumerator.MessageEnum.NOT_FOUND;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl extends DefaultServiceImpl implements UserService {

    private final CustomerService customerService;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final UserMapper userMapper;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    public UserResponseDTO register(final UserRequestDTO user) {
        log.info("[0] - Validating exists store in the database. StoreId: [{}].", user.storeId());
        final StoreModel retrieveStore = retrieveStoreById(user.storeId());
        if (Objects.isNull(retrieveStore))
            super.defaultError(NOT_FOUND, "Store", user.email(), "/store", "[POST]");
        log.info("[1] - Validating exists user ({}) to database for store ({}).", user.email(), user.storeId());
        final UserModel retrieveUser = retrieveUserByEmailAndStore(user.email(), user.storeId());
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

    private StoreModel retrieveStoreById(final Long storeId) {
        return storeRepository.retrieveById(storeId);
    }

    @Override
    @Transactional
    public UserResponseDTO retrieveByStoreIdAndUserId(final Long storeId,
                                                      final String email) {
        log.info("[1] - Retrieve user by: storeId({}) and userEmail({}).", storeId, email);
        final UserModel userModel = findById(storeId, email);
        log.info("[2] - Retrieve customer by userId.");
        final CustomerModel customerModel = findCustomerById(userModel.getUserId());
        log.info("[3] - Mapping to response.");
        return mappingResponse(userModel, userModel.getUserId(), customerModel);
    }

    private UserResponseDTO mappingResponse(final UserModel userModel,
                                            final Long userId,
                                            final CustomerModel customerModel) {
        final CustomerResponseDTO customerResponseDTO =
                customerMapper.modelToResponse(customerModel, customerModel.getCustomerId());
        return userMapper.modelToResponse(userModel, userId, customerResponseDTO);
    }

    private CustomerModel findCustomerById(final Long userId) {
        return customerService.retrieveByUserId(userId);
    }

    private UserModel findById(final Long storeId, final String email) {
        return userRepository.retrieveByEmail(email, storeId);
    }

    private Long savedUser(final UserModel userModel){
        userRepository.save(userModel);
        return retrieveUserByEmailAndStore(userModel.getEmail(), userModel.getStoreId()).getUserId();
    }

    private UserModel retrieveUserByEmailAndStore(final String email,
                                                  final Long storeId){
        return userRepository.retrieveByEmail(email, storeId);
    }

    private CustomerRequestDTO mappingCustomerFromUser(final UserModel userModel, final Long userId){
        return customerMapper.userToRequest(userModel, userId);
    }

    private CustomerResponseDTO mappingCustomerFromCustomer(final CustomerModel customerModel){
        return customerMapper.modelToResponse(customerModel, customerModel.getCustomerId());
    }
}
