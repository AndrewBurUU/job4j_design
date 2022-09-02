package ru.job4j.io;

import java.io.*;
import java.util.*;

public class LogFilter {

    public List<String> filter(String file) {
        List<String> res = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String str;
            while ((str = in.readLine()) != null) {
                String[] tmpStr = str.split(" ");
                if (tmpStr[tmpStr.length - 2].equals("404")) {
                    res.add(str + System.lineSeparator());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}
