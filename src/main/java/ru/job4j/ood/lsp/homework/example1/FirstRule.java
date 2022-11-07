package ru.job4j.ood.lsp.homework.example1;

/**
 * Предусловие
 * RailwayTransport - класс единиц ж/д транспорта
 * Tram - класс трамвая
 * FirstRule - проверочный класс.
 * Предполагается, что класс будет использоваться в РЖД для поездов, где всегда есть локомотив и вагоны.
 * Однако, кто-то решил воспользоваться готовым классом и внес изменение в предусловие. В итоге получилась аномалия.
 */
public class FirstRule {
    public static void main(String[] args) {
        RailwayTransport tram = new Tram(4);
        tram.status();
    }
}
