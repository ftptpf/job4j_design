package ru.job4j.ood.lsp.parking.space;

import ru.job4j.ood.lsp.parking.auto.Auto;

import java.util.List;

public interface Parking {

    void setParkingSize(int parkingSize);
    boolean setAutoOnParking(Auto auto);
    List<Auto> getInfoAboutAutoOnParking();

}
