package ch.hslu.AD.SW08.CarPark.parkingStrategy;

import ch.hslu.AD.SW08.CarPark.Car;
import ch.hslu.AD.SW08.CarPark.CarPark;
import ch.hslu.AD.SW08.CarPark.ParkingSimulation;

import java.util.List;

public class ClosestParkingStrategy implements ParkingStrategy {

    private List<CarPark> carParks;

    public ClosestParkingStrategy(List<CarPark> carParks) {
        this.carParks = carParks;
    }

    @Override
    public Boolean park(Car car) throws Exception {
        CarPark carPark = carParks.get(0);
        if (carPark.freeSpaces() > 0) {
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
        } else {
            if (ParkingSimulation.LOGGING) {
                System.out.println(car + " was not able to park in " + carPark);
            }
            return false;
        }
    }

}
