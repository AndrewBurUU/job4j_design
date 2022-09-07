package ru.job4j.io;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<Path, FileProperty> filesMap;
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
        if (filesMap.containsValue(newFile)) {
            for (Path mapPath : filesMap.keySet()) {
                FileProperty mapFile = filesMap.get(mapPath);
                if (newFile.equals(mapFile)) {
                    ArrayList<Path> tmpArray = new ArrayList<>();
                    if (fileDuplicatesMap.containsKey(mapFile)) {
                        tmpArray = fileDuplicatesMap.get(mapFile);
                    } else {
                        tmpArray.add(mapPath);
                    }
                    tmpArray.add(file);
                    fileDuplicatesMap.put(mapFile, tmpArray);
                }
            }
        } else {
            filesMap.put(file, newFile);
        }
        return super.visitFile(file, attrs);
    }
}
