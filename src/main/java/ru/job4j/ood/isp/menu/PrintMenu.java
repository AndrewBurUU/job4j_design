package ru.job4j.ood.isp.menu;

public class PrintMenu implements MenuPrinter {

    private final StringBuilder buffer = new StringBuilder();

    @Override
    public void print(Menu menu) {
        menu.forEach(menuItemInfo -> {
            buffer.append(
                    String.format("%s %s", menuItemInfo.getNumber(), menuItemInfo.getName()
                    ));
            buffer.append(System.lineSeparator());
        });
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

}
