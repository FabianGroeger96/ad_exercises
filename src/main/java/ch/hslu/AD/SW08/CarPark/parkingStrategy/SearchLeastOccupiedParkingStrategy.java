package ch.hslu.AD.SW08.CarPark.parkingStrategy;

import ch.hslu.AD.SW08.CarPark.Car;
import ch.hslu.AD.SW08.CarPark.CarPark;
import ch.hslu.AD.SW08.CarPark.ParkingSimulation;

import java.util.List;

public class SearchLeastOccupiedParkingStrategy implements ParkingStrategy {

    private List<CarPark> carParks;

    public SearchLeastOccupiedParkingStrategy(List<CarPark> carParks) {
        this.carParks = carParks;
    }

    @Override
    public Boolean park(Car car) throws Exception {
        int mostFreeSpots = 0;
        CarPark leastOccupiedCarPark = null;
        for (CarPark carPark : carParks) {
            if (carPark.freeSpaces() > mostFreeSpots) {
                mostFreeSpots = carPark.freeSpaces();
                leastOccupiedCarPark = carPark;
            }
        }
        if (leastOccupiedCarPark == null) {
            if (ParkingSimulation.LOGGING) {
                System.out.println(car + " was not able to find a car park");
            }
            return false;
        }
        try {
            leastOccupiedCarPark.enter(car);
        } catch (IllegalStateException e) {
            return false;
        }
        try {
            Thread.sleep(car.getParkingDuration());
        } catch (InterruptedException e) {
            return false;
        }
        leastOccupiedCarPark.exit(car);
        return true;
    }

}
