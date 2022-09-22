package ru.job4j.io.exam;

import ru.job4j.io.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class FindFiles {

    public static boolean checkPath(Path sourcePath, Path logFile) {
        boolean res = false;
        if (!sourcePath.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", sourcePath.toFile().getAbsoluteFile()));
        }
        if (!logFile.toFile().isFile()) {
            throw new IllegalArgumentException(String.format("Not file %s", logFile));
        }
        res = true;
        return res;
    }

    public static boolean checkMask(String maskType, String maskString) {
        boolean res = false;
        if ("name".equals(maskType)
                && (!Path.of(maskString).toFile().isFile())) {
            throw new IllegalArgumentException(String.format("Not file %s", maskString));
        }
        if ("mask".equals(maskType)) {
            if (!".".contains(maskString)
                    && ("*".contains(maskString) || "?".contains(maskString))) {
                throw new IllegalArgumentException(String.format("Not mask %s", maskString));
            }
            if ("regex".equals(maskType)) {
                try {
                    Pattern pattern = Pattern.compile(maskString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            res = true;
        }
        return res;
    }

    public static List<Path> getFiles(Path root, String maskType, String maskString) throws IOException {
        List<Path> res = new ArrayList<>();
        if ("name".equals(maskType)) {
            res = Search.search(root, path -> path.toFile().getName().equals(maskString));
        }
        if ("mask".equals(maskType)) {
            final PathMatcher maskMatcher = FileSystems
                    .getDefault().getPathMatcher("glob:**/" + maskString);
            res = Files.walk(root)
                    .filter(path -> maskMatcher.matches(path)).collect(Collectors.toList());
        }
        if ("regex".equals(maskType)) {
            res = Search.search(root, path -> path.toFile().getName().matches(maskString));
        }
        return res;
    }

    public static void saveToFile(List<Path> log, Path file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file.toString())
                ))) {
            log.stream().forEach(s -> out.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Incorrect parameter numbers.");
        }
        ArgsName jvm = ArgsName.of(args);
        jvm.parse(args);
        Path sourcePath = Paths.get(jvm.get("d"));
        String maskString = jvm.get("n");
        String maskType = jvm.get("t");
        Path logFile = Paths.get(jvm.get("o"));
        if (checkPath(sourcePath, logFile) && checkMask(maskType, maskString)) {
            saveToFile(getFiles(sourcePath, maskType, maskString), logFile);
        }
    }

}
