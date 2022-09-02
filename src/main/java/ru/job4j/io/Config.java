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
                if (!str.isEmpty() && !str.startsWith("#") && str.contains("=")) {
                    String[] pair = str.split("[@+=*%]");
                    values.put(pair[0], pair[1]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String value(String key) {
        throw new UnsupportedOperationException("Don't impl this method yet!");
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(newElement -> out.add(newElement));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {

        //System.out.println(new Config("app.properties"));
        String str = "111123=abcddddd";
        String[] pair = str.split("[@+=*%]");
        if (pair.length == 1)
            return;
        String delim = str.substring(pair[0].length(), str.length() - pair[1].length());
        System.out.println(pair[0]); // 123
        System.out.println(delim);   // &
        System.out.println(pair[1]); // abcd
    }
}
