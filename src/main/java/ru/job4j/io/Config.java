package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String str;
            while ((str = read.readLine()) != null) {
                if (str.isEmpty() || str.startsWith("#")) {
                    continue;
                }
                int posDelim = str.indexOf("=");
                if (posDelim <= 0 || posDelim == str.length() - 1) {
                    throw new IllegalArgumentException("Incorrect pair line: " + str);
                }
                String key = str.substring(0, posDelim);
                String value = str.substring(posDelim + 1, str.length());
                values.put(key, value);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String value(String key) {
        return values.get(key);
    }

    public Map<String, String> getValues() {
        return values;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

}
