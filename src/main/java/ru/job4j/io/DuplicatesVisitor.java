package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, ArrayList<Path>> fileDuplicatesMap;

    public DuplicatesVisitor() {
        this.fileDuplicatesMap = new LinkedHashMap<>();
    }

    public Map<FileProperty, ArrayList<Path>> getFileDuplicatesMap() {
        return fileDuplicatesMap;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty newFile = new FileProperty(file.getFileName().toString(), attrs.size());
        fileDuplicatesMap.putIfAbsent(newFile, new ArrayList<>());
        fileDuplicatesMap.get(newFile).add(file);
        return super.visitFile(file, attrs);
    }
}
