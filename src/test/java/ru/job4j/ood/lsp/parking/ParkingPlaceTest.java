package ru.job4j.ood.lsp.parking;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ood.lsp.parking.auto.Auto;
import ru.job4j.ood.lsp.parking.auto.PassengerCar;
import ru.job4j.ood.lsp.parking.space.Parking;
import ru.job4j.ood.lsp.parking.space.ParkingForPassengerCar;
import ru.job4j.ood.lsp.parking.space.ParkingForTruck;

import java.util.List;

import static org.junit.Assert.*;

public class ParkingPlaceTest {
    private Auto passengerCar1, passengerCar2, passengerCar3, passengerCar4, passengerCar5, passengerCar6, passengerCar7;
    private Auto truck1, truck2, truck3, truck4, truck5, truck6, truck7;
    private List<Auto> listAuto;
    private Parking parkingForPassengerCar, parkingForTruck;

    @Before
    public void initObjects() {
        parkingForPassengerCar = new ParkingForPassengerCar();
        parkingForTruck = new ParkingForTruck();


    }


    @Test
    public void parkingAuto() {
        parkingForPassengerCar.setParkingSize(10);
        parkingForTruck.setParkingSize(4);

    }
}