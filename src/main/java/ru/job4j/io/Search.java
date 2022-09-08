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
        if (arguments.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage ROOT_FOLDER.");
        }
        if (arguments.length == 2) {
            if (arguments[0].length() == 0 || arguments[1].length() == 0) {
                throw new IllegalArgumentException("Incorrect parameter length.");
            }
        } else {
            throw new IllegalArgumentException("Incorrect parameter numbers.");
        }
    }
}
