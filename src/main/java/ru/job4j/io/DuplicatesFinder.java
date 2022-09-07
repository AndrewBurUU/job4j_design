package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor res = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./data/tmp"), res);
        Map<FileProperty, ArrayList<Path>> resMap = res.getFileDuplicatesMap();
        for (FileProperty curFile : resMap.keySet()) {
            System.out.println(String.format("FileName: %s, FileSize: %s.", curFile.getName(), curFile.getSize()));
            System.out.println("There are some file duplicates:");
            for (Path curPath : resMap.get(curFile)) {
                System.out.println(curPath);
            }
            System.out.println();
        }
    }
}
