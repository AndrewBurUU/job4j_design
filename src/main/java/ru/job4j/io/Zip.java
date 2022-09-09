package ru.job4j.io;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.io.File;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
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

    public static boolean checkArgs(Path sourcePath, String excludeFiles, String targetFile) {
        boolean res = false;
        if (!sourcePath.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", sourcePath.toFile().getAbsoluteFile()));
        }
        if (excludeFiles.indexOf('.') == -1) {
            throw new IllegalArgumentException(String.format("File Extension is incorrect: %s", excludeFiles));
        }
        if (!targetFile.endsWith(".zip")) {
            throw new IllegalArgumentException(String.format("Zip file extension is incorrect: %s", targetFile));
        }
        res = true;
        return res;
    }

    public static void main(String[] args) throws IOException {
        ArgsName jvm = ArgsName.of(args);
        jvm.parse(args);
        Path sourcePath = Paths.get(jvm.get("d"));
        String excludeFiles = jvm.get("e");
        String targetFile = jvm.get("o");
        if (checkArgs(sourcePath, excludeFiles, targetFile)) {
            List<File> sourceFiles = new ArrayList<>();
            Search.search(sourcePath,
                    path -> !path.toFile().getName().endsWith(excludeFiles)).forEach(path -> sourceFiles.add(path.toFile()));
            packFiles(sourceFiles, new File(targetFile));
        }
    }
}
