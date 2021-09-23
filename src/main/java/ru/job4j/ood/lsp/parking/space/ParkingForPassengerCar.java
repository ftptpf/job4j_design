package ru.job4j.ood.lsp.parking.space;

import ru.job4j.ood.lsp.parking.auto.Auto;

import java.util.List;

/**
 * Парковка легковых автомобилей.
 */
public class ParkingForPassengerCar implements Parking {

    // Задаем размер парковки
    @Override
    public void setParkingSize(int parkingSize) {

    }
    // Паркуем автомобиль
    @Override
    public boolean setAutoOnParking(Auto auto) {
        return false;
    }
    // Получаем информацию о запаркованных автомобилях
    @Override
    public List<Auto> getInfoAboutAutoOnParking() {
        return null;
    }
}
