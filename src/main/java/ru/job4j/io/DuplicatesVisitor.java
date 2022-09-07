package ru.job4j.io;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, Path> filesMap;
    private Map<FileProperty, ArrayList<Path>> fileDuplicatesMap;

    public DuplicatesVisitor() {
        this.filesMap = new LinkedHashMap<>();
        this.fileDuplicatesMap = new LinkedHashMap<>();
    }

    public Map<FileProperty, ArrayList<Path>> getFileDuplicatesMap() {
        return fileDuplicatesMap;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty newFile = new FileProperty(file.getFileName().toString(), attrs.size());
        if (filesMap.containsKey(newFile)) {
            Path mapPath = filesMap.get(newFile);
            ArrayList<Path> tmpArray = new ArrayList<>();
            if (fileDuplicatesMap.containsKey(newFile)) {
                tmpArray = fileDuplicatesMap.get(newFile);
            } else {
                tmpArray.add(mapPath);
            }
            tmpArray.add(file);
            fileDuplicatesMap.put(newFile, tmpArray);
        } else {
            filesMap.put(newFile, file);
        }
        return super.visitFile(file, attrs);
    }
}
