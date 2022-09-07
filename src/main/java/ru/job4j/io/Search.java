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
        String[] realArgs = args[0].split(";");
        Path start = Paths.get(realArgs[0]);
        String ext = realArgs[1];
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
        if (arguments[0].split(";").length < 2) {
            throw new IllegalArgumentException("Root folder or file extension is null . Usage ROOT_FOLDER;.FILEEXTENSION");
        }
    }
}
