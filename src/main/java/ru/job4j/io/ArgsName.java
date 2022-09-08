package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Key not found.");
        }
        String res = values.get(key);
        if (res == null) {
            throw new IllegalArgumentException("Value not found.");
        }
        return res;
    }

    private void checkPair(String key, String value, String pair) {
        if (key.isEmpty() || value.isEmpty()) {
            throw new IllegalArgumentException(String.format("Incorrect pair: %s", pair));
        }
    }

    private void parse(String[] args) {
        /* TODO parse args to values. */
        int delim = -1;
        int keySymbolPos = -1;
        String key = "";
        String value = "";
        for (int i = 0; i < args.length; i++) {
            String pair = args[i];
            delim = pair.indexOf('=');
            keySymbolPos = pair.indexOf('-');
            if (delim > 1 && keySymbolPos == 0) {
                key = pair.substring(keySymbolPos + 1, delim);
                value = pair.substring(delim + 1, pair.length());
            }
            checkPair(key, value, pair);
            values.put(key, value);
        }

    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Args not found");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
