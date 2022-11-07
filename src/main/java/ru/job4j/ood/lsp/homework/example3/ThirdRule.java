package ru.job4j.ood.lsp.homework.example3;

/**
 * Инвариант
 * Описан класс номера автомобиля CarNumber
 * Описан класс владельца этого номера CarOwner
 * Описан класс-наследник владельца номера SomeCarOwner. В этом классе не выполнена проверка номера.
 * В этом классе (ThirdRule) сперва задается правильный номер, потом его меняют на неправильный, но программа все равно работает.
 */
public class ThirdRule {

    public static void main(String[] args) {
        CarOwner carOwner = new SomeCarOwner(
                new CarNumber(3, 520)
        );
        System.out.println(String.format("%s %s",
                carOwner.getCarNumber().getRegionCode(),
                carOwner.getCarNumber().getNumber()));

        carOwner.setCarNumber(
                new CarNumber(-1, 1000));
        System.out.println(String.format("%s %s",
                carOwner.getCarNumber().getRegionCode(),
                carOwner.getCarNumber().getNumber()));
    }
}
