package ru.job4j.serialization.json;

public class WareHouse {

    private final String github;

    public WareHouse(String github) {
        this.github = github;
    }

    public String getGithub() {
        return github;
    }

    @Override
    public String toString() {
        return "WareHouse{"
                + "github='" + github + '\''
                + '}';
    }
}
