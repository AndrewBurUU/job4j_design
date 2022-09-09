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

    private void checkPair(String pair) {
        int delim = -1;
        int keySymbolPos = -1;
        String key = "";
        String value = "";
        delim = pair.indexOf('=');
        keySymbolPos = pair.indexOf('-');
        if (delim > 1 && keySymbolPos == 0) {
            key = pair.substring(keySymbolPos + 1, delim);
            value = pair.substring(delim + 1, pair.length());
        }
        if (key.isEmpty() || value.isEmpty()) {
            throw new IllegalArgumentException(String.format("Incorrect pair: %s", pair));
        }
        values.put(key, value);
    }

    public void parse(String[] args) {
        /* TODO parse args to values. */
        for (int i = 0; i < args.length; i++) {
            checkPair(args[i]);
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
