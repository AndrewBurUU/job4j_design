package ru.job4j.io;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.io.File;
import java.nio.file.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.function.*;
import java.nio.file.Path;

public class Zip {

    public void packFiles(List<File> sources, File target) {

    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        /*
        Zip zip = new Zip();
        ArgsName jvm = ArgsName.of(args);
        Path sourcePath = Paths.get(jvm.get("d"));
        String excludeFiles = jvm.get("e");
        String targetFile = jvm.get("o");
        Search searchFiles = new Search();
        searchFiles(sourcePath, p -> p.toFile().getName().endsWith(excludeFiles)).forEach(System.out::println);
*/
    }
}
