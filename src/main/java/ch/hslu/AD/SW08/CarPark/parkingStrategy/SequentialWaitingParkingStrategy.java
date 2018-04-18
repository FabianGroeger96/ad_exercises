package ch.hslu.AD.SW08.CarPark.parkingStrategy;

import ch.hslu.AD.SW08.CarPark.Car;
import ch.hslu.AD.SW08.CarPark.CarPark;
import ch.hslu.AD.SW08.CarPark.ParkingSimulation;

import java.util.List;

public class SequentialWaitingParkingStrategy implements ParkingStrategy {

    private final List<CarPark> carParks;

    public SequentialWaitingParkingStrategy(List<CarPark> carParks) {
        this.carParks = carParks;
    }

    @Override
    public Boolean park(Car car) throws Exception {
        for (CarPark carPark : carParks) {
            if (carPark.freeSpaces() > 0) {
                return park(car, carPark);
            } else {
                try {
                    Thread.sleep(car.getWaitTolerance());
                } catch (InterruptedException e) {
                    throw new Exception(e);
                }
                if (carPark.freeSpaces() > 0) {
                    return park(car, carPark);
                }
            }
        }
        if (ParkingSimulation.LOGGING) {
            System.out.println(car + " was not able to find a car park");
        }
        return false;
    }

    private boolean park(Car car, CarPark carPark) throws Exception {
        try {
            carPark.enter(car);
        } catch (IllegalStateException e) {
            return false;
        }
        try {
            Thread.sleep(car.getParkingDuration());
        } catch (InterruptedException e) {
            throw new Exception(e);
        }
        carPark.exit(car);
        return true;
    }
}
