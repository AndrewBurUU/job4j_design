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
        Map<Path, FileProperty> tmpMap = new LinkedHashMap<>();
        for (Map.Entry<Path, FileProperty> tmp : filesMap.entrySet()) {
            Path tmpPath = tmp.getKey();
            FileProperty tmpFileProperty = tmp.getValue();
            tmpMap.put(tmpPath, tmpFileProperty);
        }

        for (Path curFilePath : filesMap.keySet()) {
            FileProperty curFile = filesMap.get(curFilePath);
            tmpMap.remove(curFilePath);
            if (tmpMap.containsValue(curFile)) {
                ArrayList<Path> tmpArray = new ArrayList<>();
                tmpArray.add(curFilePath);
                for (Path tmpFilePath : tmpMap.keySet()) {
                    FileProperty tmpFile = tmpMap.get(tmpFilePath);
                    if (curFile.equals(tmpFile)) {
                        tmpArray.add(tmpFilePath);
                        tmpMap.put(tmpFilePath, null);
                    }
                }
                fileDuplicatesMap.put(curFile, tmpArray);
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
