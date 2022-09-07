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
        for (Path curFilePath : filesMap.keySet()) {
            FileProperty curFile = filesMap.get(curFilePath);
            for (Path duplFilePath : filesMap.keySet()) {
                FileProperty duplFile = filesMap.get(duplFilePath);
                if (!curFilePath.equals(duplFilePath)) {
                    if (curFile.equals(duplFile)) {
                        ArrayList<Path> tmpAr = new ArrayList<>();
                        if (fileDuplicatesMap.containsKey(curFile)) {
                            tmpAr = fileDuplicatesMap.get(curFile);
                        }
                        tmpAr.add(curFilePath);
                        fileDuplicatesMap.put(curFile, tmpAr);
                    }
                }
            }
        }
        return fileDuplicatesMap;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty newFile = new FileProperty(file.getFileName().toString(), attrs.size());
        filesMap.put(file, newFile);
        return super.visitFile(file, attrs);
    }
}
