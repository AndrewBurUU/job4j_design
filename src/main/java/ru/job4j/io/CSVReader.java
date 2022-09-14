package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {

    public static void argumentsValidate(ArgsName arguments) {
        File fileIn = new File(arguments.get("path"));
        if (!fileIn.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", fileIn.getAbsoluteFile()));
        }
        if (arguments.get("delimiter").length() != 1) {
            throw new IllegalArgumentException("Delimiter length must be equal to one");
        }
        if (!"stdout".equals(arguments.get("out"))) {
            File fileOut = new File(arguments.get("out"));
            if (fileIn.equals(fileOut)) {
                throw new IllegalArgumentException("FileIn and FileOut must be different");
            }
        }
        String filter = arguments.get("filter");
        if (filter.length() == 0) {
            throw new IllegalArgumentException("Empty filter fields");
        } else {
            if (filter.indexOf(",") == -1) {
                throw new IllegalArgumentException(String.format("Illegal filter fields delimiter: %s", filter));
            }
        }
    }

    public static void handle(ArgsName argsName) throws Exception {
        String[] filterNames = argsName.get("filter").split(",");
        int countLines = 0;
        List<Integer> resIndex = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(argsName.get("path")));
             PrintStream out = new PrintStream("stdout".equals(argsName.get("out"))
                              ? System.out
                              : new FileOutputStream(argsName.get("out")))
        ) {
            String sourceString = "";
            while ((sourceString = in.readLine()) != null) {
                var scanner = new Scanner(new ByteArrayInputStream(sourceString.getBytes()))
                        .useDelimiter(argsName.get("delimiter"));
                List<String> fieldsList = new ArrayList<>();
                String res = "";
                while (scanner.hasNext()) {
                    fieldsList.add(scanner.next());
                }
                if (countLines == 0) {
                    for (int i = 0; i < filterNames.length; i++) {
                        int index = fieldsList.indexOf(filterNames[i]);
                        if (index >= 0) {
                            resIndex.add(index);
                        }
                    }
                }
                for (int i = 0; i < resIndex.size(); i++) {
                    int index = resIndex.get(i);
                    if (res.length() == 0) {
                        res = fieldsList.get(index);
                    } else {
                        res = res + ";" + fieldsList.get(index);
                    }
                }
                out.println(res);
                countLines++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readLog(File file) {
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().forEach(s -> System.out.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Incorrect parameter numbers.");
        }
        ArgsName jvm = ArgsName.of(args);
        argumentsValidate(jvm);
        handle(jvm);
        readLog(new File(jvm.get("out")));
    }
}
