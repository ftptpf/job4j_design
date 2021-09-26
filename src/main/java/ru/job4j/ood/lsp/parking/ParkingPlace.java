package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.auto.Auto;
import ru.job4j.ood.lsp.parking.space.Parking;

import java.util.List;

public class ParkingPlace {
    Parking passengerCarParking;
    Parking truckParking;

    public ParkingPlace(Parking passengerCarParking, Parking truckParking) {
        this.passengerCarParking = passengerCarParking;
        this.truckParking = truckParking;
    }

    /**
     * Паркуем легковые и грузовые автомобили
     * @param listAuto
     * @return
     */
    boolean parkingAuto(List<Auto> listAuto) {
        boolean result = true;
        for (Auto auto: listAuto) {
            int autoSize = auto.getCarSize();
            if (autoSize > 1) {
                if (truckParking.getFreeSpace() >= 1) {
                    result = truckParking.parkingAuto(auto);
                } else {
                    result = passengerCarParking.parkingAuto(auto);
                }
            } else if (autoSize == 1) {
                result = passengerCarParking.parkingAuto(auto);
            }
        }
        return result;
    }

    /**
     * Список авто с парковки легковых автомобилей
     * @return
     */
    public List<Auto> getListAutoFromPassengerParking() {
        return passengerCarParking.getInfoAboutAutoOnParking();
    }

    /**
     * Список авто с пакрковки грузовых автомобилей
     * @return
     */
    public List<Auto> getListAutoFromTruckParking() {
        return truckParking.getInfoAboutAutoOnParking();
    }
}
