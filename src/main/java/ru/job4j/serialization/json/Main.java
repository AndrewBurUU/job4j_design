package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String[] args) {
        final Student student = new Student(true, "Andrew Johnson", 21,
                new WareHouse("https://github.com/CodePlay"),
                new String[] {"C#", "Java", "JS"});
        final Gson gsonStudent = new GsonBuilder().create();
        final Student studentJson = gsonStudent.fromJson(gsonStudent.toJson(student), Student.class);
        System.out.println(studentJson);
    }
}
