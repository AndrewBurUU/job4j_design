package ru.job4j.ood.isp.menu;

public class PrintMenu implements MenuPrinter {

    private static final String DELIMITER = "----";
    private final StringBuilder buffer = new StringBuilder();

    private String repeatDelimiter(String str) {
        int count = str.split("\\.").length;
        return count == 0 ? "" : DELIMITER.repeat(count - 1);
    }

    @Override
    public void print(Menu menu) {
        menu.forEach(menuItemInfo -> {
            buffer.append(
                    String.format("%s %s %s",
                            repeatDelimiter(menuItemInfo.getNumber()),
                            menuItemInfo.getNumber(),
                            menuItemInfo.getName()
                    ));
            buffer.append(System.lineSeparator());
        });
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

}
