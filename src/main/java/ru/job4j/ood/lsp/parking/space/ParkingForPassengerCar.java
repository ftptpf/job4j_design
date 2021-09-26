package ru.job4j.ood.lsp.parking.space;

import ru.job4j.ood.lsp.parking.auto.Auto;

import java.util.ArrayList;
import java.util.List;

/**
 * Парковка легковых автомобилей.
 */
public class ParkingForPassengerCar implements Parking {
    private int freeSpace;
    private List<Auto> carList = new ArrayList<>();
    private List<Auto> trackList = new ArrayList<>();

    public ParkingForPassengerCar(int parkingSize) {
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
        int autoSize = auto.getCarSize();
        if (autoSize == 1 && freeSpace >= autoSize) {
            carList.add(auto);
            freeSpace = freeSpace - 1;
            result = true;
        }
        if (autoSize > 1 && freeSpace >= autoSize) {
            trackList.add(auto);
            freeSpace = freeSpace - autoSize;
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
        List<Auto> list = new ArrayList<>();
        list.addAll(carList);
        list.addAll(trackList);
        return list;
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
