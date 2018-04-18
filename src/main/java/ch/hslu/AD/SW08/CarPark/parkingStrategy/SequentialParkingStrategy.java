package ch.hslu.AD.SW08.CarPark.parkingStrategy;

import ch.hslu.AD.SW08.CarPark.Car;
import ch.hslu.AD.SW08.CarPark.CarPark;
import ch.hslu.AD.SW08.CarPark.ParkingSimulation;

import java.util.List;

public class SequentialParkingStrategy implements ParkingStrategy {

    private List<CarPark> carParks;

    public SequentialParkingStrategy(List<CarPark> carParks) {
        this.carParks = carParks;
    }

    @Override
    public Boolean park(Car car) throws Exception {
        for (CarPark carPark : carParks) {
            if (carPark.freeSpaces() > 0) {
                try {
                    carPark.enter(car);
                } catch (IllegalStateException ex) {
                    return false;
                }
                Thread.sleep(car.getParkingDuration());
                carPark.exit(car);
                return true;
            }
        }
        if (ParkingSimulation.LOGGING) {
            System.out.println(car + " was not able to find a car park");
        }
        return false;
    }
}
