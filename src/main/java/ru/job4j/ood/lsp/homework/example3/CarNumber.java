package ru.job4j.ood.lsp.homework.example3;

public class CarNumber {

    private int regionCode;

    private int number;

    public CarNumber(int regionCode, int number) {
        this.regionCode = regionCode;
        this.number = number;
    }


    public int getRegionCode() {
        return regionCode;
    }

    public int getNumber() {
        return number;
    }
}
