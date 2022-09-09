package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.*;

public class Search {

    public static void main(String[] args) throws IOException {
        argumentsValidate(args);
        Path start = Paths.get(args[0]);
        String ext = args[1];
        search(start, p -> p.toFile().getName().endsWith(ext)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void argumentsValidate(String[] arguments) {
        if (arguments.length != 2) {
            throw new IllegalArgumentException("Incorrect parameter numbers.");
        }
        File file = new File(arguments[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        String ext = arguments[1];
        if (ext.indexOf('.') == -1) {
            throw new IllegalArgumentException(String.format("File Extension is incorrect: %s", ext));
        }
    }
}
