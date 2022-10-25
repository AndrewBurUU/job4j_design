package ru.job4j.gc.cache.menu;

import ru.job4j.gc.cache.*;
import java.util.*;

public class Emulator {

    public static final int CHOOSE_DIR = 1;
    public static final int PUT_FILE = 2;
    public static final int GET_FILE = 3;

    public static final String SELECT = "Выберите меню";
    public static final String TEXT_OF_DIR = "Укажите кэшируемую директорию";
    public static final String TEXT_FILENAME = "Введите название файла";
    public static final String TEXT_GET_FILE = "Загрузка содержимого файла из кэша";
    public static final String EXIT = "Конец работы";

    public static final String MENU = """
                Введите 1 для директории.
                Введите 2 для загрузки файла.
                Введите 3 для отображения файла.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String dirName = "./data/files/";
        DirFileCache dirFileCache = new DirFileCache(dirName);
        System.out.println(String.format("Текущая директория: %s", dirName));
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (CHOOSE_DIR == userChoice) {
                System.out.println(TEXT_OF_DIR);
                dirName = scanner.nextLine();
                dirFileCache = new DirFileCache(dirName);
                System.out.println("Принято");
            } else if (PUT_FILE == userChoice) {
                System.out.println(TEXT_FILENAME);
                String fileName = scanner.nextLine();
                dirFileCache.get(fileName);
                System.out.println("Принято");
            } else if (GET_FILE == userChoice) {
                System.out.println(TEXT_FILENAME);
                String fileName = scanner.nextLine();
                System.out.println(TEXT_GET_FILE);
                System.out.println(dirFileCache.get(fileName));
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

}
