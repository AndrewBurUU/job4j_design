package ru.job4j.ood.lsp.homework.example3;

public class CarOwner {

    protected CarNumber carNumber;

    public CarOwner(CarNumber carNumber) {
        validate(carNumber);
        this.carNumber = carNumber;
    }

    protected void validate(CarNumber carNumber) {
        if (carNumber.getRegionCode() < 1 || carNumber.getRegionCode() > 999) {
            throw new IllegalArgumentException("Invalid country code!");
        }
        if (carNumber.getNumber() < 1 || carNumber.getNumber() > 999) {
            throw new IllegalArgumentException("Invalid number!");
        }
    }

    public CarNumber getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(CarNumber carNumber) {
        validate(carNumber);
        this.carNumber = carNumber;
    }
}
