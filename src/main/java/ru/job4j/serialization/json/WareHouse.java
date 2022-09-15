package ru.job4j.serialization.json;

public class WareHouse {

    private final String github;

    public WareHouse(String github) {
        this.github = github;
    }

    @Override
    public String toString() {
        return "WareHouse{"
                + "github='" + github + '\''
                + '}';
    }
}
