package com.dev.torhugo.hub_payments.service.impl;

import com.dev.torhugo.hub_payments.lib.data.enumerator.MessageEnum;
import com.dev.torhugo.hub_payments.lib.exception.impl.DataBaseException;
import com.dev.torhugo.hub_payments.service.DefaultService;
import com.dev.torhugo.hub_payments.util.GenericUtil;
import org.springframework.stereotype.Component;

import static com.dev.torhugo.hub_payments.lib.data.enumerator.MessageEnum.CONTACT_SUPPORT;

@Component
public abstract class DefaultServiceImpl implements DefaultService {

    public static final String DEFAULT_HOST = "http://localhost:8080/api/v1";

    @Override
    public void defaultError(final MessageEnum messageEnum,
                             final Object classError,
                             final String identifier,
                             final String path,
                             final String method) {
        throw new DataBaseException(
                messageEnum.getDescription(classError.toString(), identifier),
                CONTACT_SUPPORT.getDescription(),
                GenericUtil.genericHost(DEFAULT_HOST, path),
                method);
    }
}
