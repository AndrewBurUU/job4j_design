package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte typeByte = 10;
        short typeShort = 100;
        int typeInt = 1000;
        long typeLong = 1000;
        float typeFloat = 1000;
        double typeDouble = 1000;
        char typeChar = 'z';
        boolean typeBoolean = true;
        LOG.debug("Byte: {}, short: {}, int: {}, long: {}, "
                        + "float: {}, double: {}, char: {}, boolean: {}",
                typeByte, typeShort, typeInt, typeLong,
                typeFloat, typeDouble, typeChar, typeBoolean);
    }
}
