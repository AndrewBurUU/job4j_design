package ru.job4j.gc.leak;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;

public interface Generate {

    void generate();

    default List<String> read(String path) throws IOException {
        List<String> text = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            lines.forEach(text::add);
        }
        return text;
    }

}
