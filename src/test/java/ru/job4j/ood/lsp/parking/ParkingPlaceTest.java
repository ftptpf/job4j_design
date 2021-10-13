package ru.job4j.ood.lsp.parking;

import org.junit.Before;
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
    int truckSize;

    @Before
    public void initObjects() {
        passengerCar1 = new PassengerCar();
        passengerCar2 = new PassengerCar();
        passengerCar3 = new PassengerCar();
        passengerCar4 = new PassengerCar();
        passengerCar5 = new PassengerCar();
        truckSize = 2;
        truck1 = new Truck(truckSize);
        truck2 = new Truck(truckSize);
        truck3 = new Truck(truckSize);
        truck4 = new Truck(truckSize);
        truck5 = new Truck(truckSize);
        listPassengerCar = List.of(passengerCar1, passengerCar2, passengerCar3, passengerCar4, passengerCar5);
        listTruck2 = List.of(truck1, truck2, truck3, truck4, truck5);
        listAuto = new ArrayList<>();
        listAuto.addAll(listPassengerCar);
        listAuto.addAll(listTruck2);
    }

    @Test
    public void enoughSpaceOnEachParking() {
        parkingForPassengerCar = new ParkingForPassengerCar(10);
        parkingForTruck = new ParkingForTruck(6);
        parkingPlace = new ParkingPlace(parkingForPassengerCar, parkingForTruck);
        assertTrue(parkingPlace.parkingAuto(listAuto));
        assertTrue(parkingForPassengerCar.getFreeSpace() > 0);
        assertTrue(parkingForTruck.getFreeSpace() > 0);
    }

    @Test
    public void enoughSpaceButSumTrucksParkingOnPassengerPlaces() {
        parkingForPassengerCar = new ParkingForPassengerCar(10);
        parkingForTruck = new ParkingForTruck(3);
        parkingPlace = new ParkingPlace(parkingForPassengerCar, parkingForTruck);
        assertTrue(parkingPlace.parkingAuto(listAuto));
        assertEquals(1, parkingForPassengerCar.getFreeSpace());
        assertEquals(0, parkingForTruck.getFreeSpace());
    }

    @Test
    public void notEnoughSpaceOnParkingForTrucks() {
        parkingForPassengerCar = new ParkingForPassengerCar(6);
        parkingForTruck = new ParkingForTruck(3);
        parkingPlace = new ParkingPlace(parkingForPassengerCar, parkingForTruck);
        assertFalse(parkingPlace.parkingAuto(listAuto));
    }

    @Test
    public void truckParkingOnTwoPassengersPlaces() {
        parkingForPassengerCar = new ParkingForPassengerCar(5 + truckSize);
        parkingForTruck = new ParkingForTruck(5);
        parkingPlace = new ParkingPlace(parkingForPassengerCar, parkingForTruck);
        assertTrue(parkingPlace.parkingAuto(listAuto));
        assertEquals(truckSize, parkingForPassengerCar.getFreeSpace());
        parkingForPassengerCar = new ParkingForPassengerCar(5 + truckSize);
        parkingForTruck = new ParkingForTruck(4);
        parkingPlace = new ParkingPlace(parkingForPassengerCar, parkingForTruck);
        assertTrue(parkingPlace.parkingAuto(listAuto));
        assertEquals(0, parkingForPassengerCar.getFreeSpace());
    }
}