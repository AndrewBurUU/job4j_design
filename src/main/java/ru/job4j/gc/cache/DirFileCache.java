package ru.job4j.gc.cache;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) throws IOException {
        return Files.readString(Path.of(cachingDir, key));
    }

}
