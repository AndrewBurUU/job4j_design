package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "warehouse")
public class WareHouse {

    @XmlAttribute
    private String github;

    public WareHouse() {

    }

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
