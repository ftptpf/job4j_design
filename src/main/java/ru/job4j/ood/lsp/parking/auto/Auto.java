package ru.job4j.ood.lsp.parking.auto;

import java.util.Objects;

public class Auto {
    int carSize;

    public Auto() {
    }

    public Auto(int carSize) {
        this.carSize = carSize;
    }

    public int getCarSize() {
        return carSize;
    }

    public void setCarSize(int carSize) {
        this.carSize = carSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Auto auto = (Auto) o;
        return carSize == auto.carSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(carSize);
    }
}
