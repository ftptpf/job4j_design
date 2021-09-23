package ru.job4j.ood.lsp.parking;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.ood.lsp.parking.auto.Auto;
import ru.job4j.ood.lsp.parking.auto.PassengerCar;
import ru.job4j.ood.lsp.parking.auto.Truck;
import ru.job4j.ood.lsp.parking.space.Parking;
import ru.job4j.ood.lsp.parking.space.ParkingForPassengerCar;
import ru.job4j.ood.lsp.parking.space.ParkingForTruck;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParkingPlaceTest {
    private Auto passengerCar1, passengerCar2, passengerCar3, passengerCar4, passengerCar5;
    private Auto truck1, truck2, truck3, truck4, truck5;
    private List<Auto> listAuto;
    private List<Auto> listPassengerCar;
    private List<Auto> listTruck2;
    private Parking parkingForPassengerCar, parkingForTruck;
    ParkingPlace parkingPlace;

    @Before
    public void initObjects() {
        passengerCar1 = new PassengerCar(1);
        passengerCar2 = new PassengerCar(1);
        passengerCar3 = new PassengerCar(1);
        passengerCar4 = new PassengerCar(1);
        passengerCar5 = new PassengerCar(1);
        truck1 = new Truck(2);
        truck2 = new Truck(2);
        truck3 = new Truck(2);
        truck4 = new Truck(2);
        truck5 = new Truck(2);
        listPassengerCar = List.of(passengerCar1, passengerCar2, passengerCar3, passengerCar4, passengerCar5);
        listTruck2 = List.of(truck1, truck2, truck3, truck4, truck5);
        listAuto = new ArrayList<>();
        listAuto.addAll(listPassengerCar);
        listAuto.addAll(listTruck2);
        parkingForPassengerCar = new ParkingForPassengerCar();
        parkingForTruck = new ParkingForTruck();
    }

    @Test
    @Ignore
    public void enoughSpaceOnEachParking() {
        parkingForPassengerCar.setParkingSize(10);
        parkingForTruck.setParkingSize(6);
        parkingPlace = new ParkingPlace(parkingForPassengerCar, parkingForTruck); // passenger cars = 5, trucks = 5
        assertTrue(parkingPlace.parkingAuto(listAuto));
        assertTrue(parkingPlace.getInfoFromPassengerCarParking().size() <= parkingForPassengerCar.getInfoAboutAutoOnParking().size());
        assertTrue(parkingPlace.getInfoFromTruckParking().size() <= parkingForTruck.getInfoAboutAutoOnParking().size());
    }

    @Test
    @Ignore
    public void enoughSpaceButSumTrucksParkingOnPassengerPlaces() {
        parkingForPassengerCar.setParkingSize(10);
        parkingForTruck.setParkingSize(3);
        parkingPlace = new ParkingPlace(parkingForPassengerCar, parkingForTruck); // passenger cars = 5, trucks = 5
        assertTrue(parkingPlace.parkingAuto(listAuto));
        assertEquals(parkingPlace.getInfoFromTruckParking().size(), parkingForTruck.getInfoAboutAutoOnParking().size());
        assertTrue(parkingPlace.getInfoFromPassengerCarParking().contains(new Truck(2)));
    }

    @Test
    @Ignore
    public void notEnoughSpaceOnParkingForTrucks() {
        parkingForPassengerCar.setParkingSize(6);
        parkingForTruck.setParkingSize(3);
        parkingPlace = new ParkingPlace(parkingForPassengerCar, parkingForTruck); // passenger cars = 5, trucks = 5
        assertFalse(parkingPlace.parkingAuto(listAuto));
    }
}