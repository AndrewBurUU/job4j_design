package ru.job4j.ood.lsp.homework.example3;

public class SomeCarOwner extends CarOwner {

    public SomeCarOwner(CarNumber carNumber) {
        super(carNumber);
    }

    @Override
    public void setCarNumber(CarNumber carNumber) {
        /**Инвариант: пропущена проверка правильности номера машины*/
        this.carNumber = carNumber;
    }
}
