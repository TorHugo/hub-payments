package com.dev.torhugo.hub_payments.util;

import com.dev.torhugo.hub_payments.lib.data.enumerator.MessageEnum;

import java.util.Objects;

import static com.dev.torhugo.hub_payments.lib.data.enumerator.MessageEnum.NOT_APPLICABLE;

public class GenericUtil {

    private GenericUtil() {    }

    public static String genericHost(final String host, final String path){
        if (Objects.nonNull(host) && Objects.nonNull(path))
                return host.concat(path);
        return NOT_APPLICABLE.getDescription();
    }

}
