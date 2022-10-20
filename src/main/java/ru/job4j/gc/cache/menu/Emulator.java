package ru.job4j.gc.cache.menu;

import ru.job4j.gc.cache.*;

public class Emulator {

    public static void main(String[] args) {
        DirFileCache dirFileCache = new DirFileCache("./data/files/");
        String fileName = "names.txt";
        dirFileCache.put(fileName);
        System.out.println(dirFileCache.get(fileName));
        fileName = "addresses.txt";
        dirFileCache.put(fileName);
        System.out.println(dirFileCache.get(fileName));
    }
}
