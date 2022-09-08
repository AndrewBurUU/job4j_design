package ru.job4j.io;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.io.File;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.nio.file.Files;
import java.util.*;

public class Zip {

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName jvm = ArgsName.of(args);
        jvm.parse(args);
        Path sourcePath = Paths.get(jvm.get("d"));
        String excludeFiles = jvm.get("e");
        String targetFile = jvm.get("o");
        /*
        Search searchFiles = new Search();
        List<File> sourceFiles = searchFiles(sourcePath,
                p -> p.toFile().getName().endsWith(excludeFiles))
                .collect(Collectors.toList());
        */
        List<File> sourceFiles = new ArrayList<>();
        SearchFiles searcher = new SearchFiles(p -> !p.toFile().getName().endsWith(excludeFiles));
        Files.walkFileTree(sourcePath, searcher);
        for (Path path : searcher.getPaths()) {
             sourceFiles.add(path.toFile());
        }
        packFiles(sourceFiles, new File(targetFile));
    }
}
