package ru.job4j.gc.cache;

import java.io.*;
import java.util.*;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String fileName = String.format("%s%s", cachingDir, key);
        StringJoiner res = new StringJoiner("; ");
        try (BufferedReader in = new BufferedReader(
                new FileReader(fileName))) {
            in.lines().forEach(s -> res.add(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res.toString();
    }

}
