package ru.job4j.ood.isp.menu;

import java.util.*;

public class TODOApp extends SimpleMenu {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    public static final int ADD_ROOT = 1;
    public static final int ADD_NODE = 2;
    public static final int SHOW_MENU = 3;

    public static final String PARENT_ROOT = " Добавить пункт меню в корень: ";

    public static final String PARENT = " Добавить пункт меню: ";

    public static final String CHILD = " Добавить подпункт меню: ";

    public static final String SELECT = "Вывести меню: ";

    public static final String MENU = """
                Введите 1, чтобы добавить пункт в корень.
                Введите 2, чтобы добавить подпункт.
                Введите 3, чтобы отобразить меню.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (ADD_ROOT == userChoice) {
                System.out.print(PARENT_ROOT);
                String parent = scanner.nextLine();
                menu.add(Menu.ROOT, parent, STUB_ACTION);
            } else if (ADD_NODE == userChoice) {
                System.out.print(PARENT);
                String parent = scanner.nextLine();
                System.out.print(CHILD);
                String child = scanner.nextLine();
                menu.add(parent, child, STUB_ACTION);
            } else if (SHOW_MENU == userChoice) {
                System.out.println(SELECT);
                PrintMenu printMenu = new PrintMenu();
                printMenu.print(menu);
                System.out.println(printMenu);
            } else {
                run = false;
                System.out.println("Конец работы");
            }
        }
    }
}
