package ru.job4j.io;

import java.io.*;

public class Analizy {

    public static void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String unavailLine = "";
            for (String str : in.lines().toList()) {
                String[] arLine = str.split(" ");
                String codeLine = arLine[0];
                String timeLine = arLine[1];
                if ((("400").equals(codeLine) || ("500").equals(codeLine))
                && unavailLine.isEmpty()
                ) {
                    unavailLine = timeLine;
                } else if ((("200").equals(codeLine) || ("300").equals(codeLine))
                && unavailLine.length() > 0
                ) {
                        out.println(unavailLine + ";" + timeLine + ";");
                        unavailLine = "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server.log", "./data/unavailable.csv");
        analizy.unavailable("./data/server1.log", "./data/unavailable1.csv");
        analizy.unavailable("./data/server2.log", "./data/unavailable2.csv");
    }
}
