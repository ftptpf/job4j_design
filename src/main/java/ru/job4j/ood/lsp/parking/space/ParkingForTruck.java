package ru.job4j.ood.lsp.parking.space;

import ru.job4j.ood.lsp.parking.auto.Auto;

import java.util.List;

/**
 * Парковка грузовых автомобилей.
 */
public class ParkingForTruck implements Parking {

    //Задаем размер парковки
    @Override
    public void setParkingSize(int parkingSize) {

    }

    @Override
    public boolean setAutoOnParking(Auto auto) {
        return false;
    }

    @Override
    public List<Auto> getInfoAboutAutoOnParking() {
        return null;
    }
}
