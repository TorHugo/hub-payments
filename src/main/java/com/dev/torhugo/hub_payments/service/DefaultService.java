package com.dev.torhugo.hub_payments.service;

import com.dev.torhugo.hub_payments.lib.data.enumerator.MessageEnum;

public interface DefaultService {
    /**
     * It creates a default error that expects the used class, an error identifier, a potential error identifier for error return. <br/>
     * And also returns (if available) a URL for error correction.
     *
     * @param messageEnum the class messages
     * @param classError the class error
     * @param identifier the identifier
     * @param path       the path
     * @param method     the method
     */
    void defaultError(final MessageEnum messageEnum,
                      final Object classError,
                      final String identifier,
                      final String path,
                      final String method);
}
