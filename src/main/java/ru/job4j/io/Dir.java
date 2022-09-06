package ru.job4j.io;

import java.io.File;

public class Dir {

    public static void main(String[] args) {
        File file = new File("d:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subfile : file.listFiles()) {
            if (subfile.isFile()) {
                System.out.println(String.format("filename: %s, size: %s", subfile.getName(), subfile.length()));
            }
        }
    }
}
