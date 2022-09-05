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
                if (("404").equals(tmpStr[tmpStr.length - 2])) {
                    res.add(str + System.lineSeparator());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            out.println(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        log.stream().forEach(System.out::print);
    }
}
