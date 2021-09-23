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
    // Паркуем автомобили
    boolean parkingAuto(List<Auto> listAuto) {
        return true;
    }
    // Получаем список автомобилей с парковки пассажирских авто
    List<Auto> getInfoFromPassengerCarParking() {
        return null;
    }
    // Получаем список автомобилей с парковки грузовиков
    List<Auto> getInfoFromTruckParking() {
        return null;
    }
}
