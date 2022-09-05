package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                if (read == 13) {
                    int num = Integer.parseInt(text.toString());
                    text.delete(0, text.length());
                    if (num % 2 == 0) {
                        System.out.println(num + " is even");
                    } else {
                        System.out.println(num + " is odd");
                    }
                } else if (Character.isDigit((char) read)) {
                    text.append((char) read);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
