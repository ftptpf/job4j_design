package ru.job4j.ood.lsp.parking.space;

import ru.job4j.ood.lsp.parking.auto.Auto;

import java.util.List;

/**
 * Парковка грузовых автомобилей.
 */
public class ParkingForTruck implements Parking {

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
    // Получаем информацию о количестве свободного места на парковке
    @Override
    public int getFreeSpace() {
        return 0;
    }
}
