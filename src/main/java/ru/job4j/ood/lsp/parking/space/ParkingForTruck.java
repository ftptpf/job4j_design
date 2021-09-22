package ru.job4j.ood.lsp.parking.space;

import ru.job4j.ood.lsp.parking.auto.Auto;

import java.util.List;

public class ParkingForTruck implements Parking {
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
