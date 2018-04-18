package ch.hslu.AD.SW08.CarPark.parkingStrategy;

import ch.hslu.AD.SW08.CarPark.Car;

public interface ParkingStrategy {

    public Boolean park(Car car) throws Exception;
}
