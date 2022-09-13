package ru.job4j.io;

import java.util.*;
import java.util.stream.*;

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

    private boolean checkPair(String pair) {
        if (!pair.contains("=")) {
            throw new IllegalArgumentException(
                    String.format("this name: %s does not contain the symbol \"=\"", pair));
        }
        if (!pair.startsWith("-")) {
            throw new IllegalArgumentException(
                    String.format("this name: %s does not contain the key symbol \"-\"", pair));
        }
        if (pair.startsWith("=") || pair.startsWith("-=")) {
            throw new IllegalArgumentException(
                    String.format("this name: %s does not contain a key", pair));
        }
        if (pair.indexOf("=") == pair.length() - 1) {
            throw new IllegalArgumentException(
                    String.format("this name: %s does not contain a value", pair));
        }
        return true;
    }

    public void parse(String[] args) {
        /* TODO parse args to values. */
        if (args.length == 0) {
            throw new IllegalArgumentException("Names array is empty");
        }
        values.putAll(Arrays.stream(args)
                .map(String::trim)
                .filter(this::checkPair)
                .map(s -> s.split("=", 2))
                .collect(Collectors.toMap(
                        e -> e[0].substring(1, e[0].length()),
                        e -> e[1],
                        (first, second) -> String.format("%s+%s", first, second)
                )));
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
