package com.dev.torhugo.hub_payments.util;

public class GenericUtil {

    private GenericUtil() {    }

    public static String genericHost(final String host, final String path){
        return host.concat(path);
    }
}
