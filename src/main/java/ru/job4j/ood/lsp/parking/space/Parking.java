package ru.job4j.ood.lsp.parking.space;

import ru.job4j.ood.lsp.parking.auto.Auto;

import java.util.List;

public interface Parking {

    boolean parkingAuto(Auto auto);
    List<Auto> getInfoAboutAutoOnParking();
    int getFreeSpace();

}
