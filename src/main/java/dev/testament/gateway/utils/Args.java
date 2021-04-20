package dev.testament.gateway.utils;

public final class Args {

    public static void notNull(String name, Object value) {
        if(value == null)
            throw new NullPointerException("'" + name + "' cannot be null");
    }

}
