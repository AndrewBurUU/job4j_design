package ru.job4j.serialization.json;

import org.json.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        /* JSONObject из json-строки строки */
        JSONObject jsonWareHouse = new JSONObject("{\"github\":\"https://github.com/CodePlay\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("C#");
        list.add("Java");
        list.add("JS");
        JSONArray jsonLangs = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Student student = new Student(true, "Andrew Johnson", 21,
                new WareHouse("https://github.com/CodePlay"),
                "C#", "Java", "JS");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("outoftown", student.isOutOfTown());
        jsonObject.put("name", student.getName());
        jsonObject.put("age", student.getAge());
        jsonObject.put("warehouse", jsonWareHouse);
        jsonObject.put("langs", jsonLangs);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект student в json-строку */
        System.out.println(new JSONObject(student).toString());
    }
}
