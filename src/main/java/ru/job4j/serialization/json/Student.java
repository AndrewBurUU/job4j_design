package ru.job4j.serialization.json;

import java.util.Arrays;

public class Student {

    private final boolean outOfTown;
    private final String name;
    private final int age;
    private final WareHouse wareHouse;
    private final String[] langs;

    public Student(boolean outOfTown, String name, int age, WareHouse wareHouse, String... langs) {
        this.outOfTown = outOfTown;
        this.name = name;
        this.age = age;
        this.wareHouse = wareHouse;
        this.langs = langs;
    }

    public boolean isOutOfTown() {
        return outOfTown;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public String[] getLangs() {
        return langs;
    }

    @Override
    public String toString() {
        return "Student{"
                + "outoftown=" + outOfTown
                + ", name=" + name
                + ", age=" + age
                + ", warehouse=" + wareHouse
                + ", langs=" + Arrays.toString(langs)
                + '}';
    }
}
