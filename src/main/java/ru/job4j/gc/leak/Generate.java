package ru.job4j.gc.leak;

import ru.job4j.serialization.json.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public interface Generate {

    void generate();

    default List<String> read(String path) throws IOException {
        List<String> text = new ArrayList<>();
        try(BufferedReader in = new BufferedReader(new FileReader(path))) {
            in.lines().forEach(text::add);
        }
        return text;
    }

}