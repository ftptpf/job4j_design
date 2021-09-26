package ru.job4j.ood.lsp.parking.space;

import ru.job4j.ood.lsp.parking.auto.Auto;

import java.util.ArrayList;
import java.util.List;

/**
 * Парковка грузовых автомобилей.
 */
public class ParkingForTruck implements Parking {
    private int freeSpace;
    private List<Auto> trackList = new ArrayList<>();

    public ParkingForTruck(int parkingSize) {
        this.freeSpace = parkingSize;
    }

    /**
     * Паркуем автомобиль
     * @param auto
     * @return
     */
    @Override
    public boolean parkingAuto(Auto auto) {
        boolean result = false;
        if (auto.getCarSize() > 1 && freeSpace >= 1) {
            trackList.add(auto);
            freeSpace = freeSpace - 1;
            result = true;
        }
        return result;
    }

    /**
     * Список запаркованных автомобилей
     * @return
     */
    @Override
    public List<Auto> getInfoAboutAutoOnParking() {
        return trackList;
    }

    /**
     * Получаем информацию о количестве свободного места на парковке
     * @return
     */
    @Override
    public int getFreeSpace() {
        return freeSpace;
    }
}
