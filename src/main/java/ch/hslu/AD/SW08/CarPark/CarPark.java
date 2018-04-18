package ch.hslu.AD.SW08.CarPark;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Übung: Weiterführende Konzepte (N3)
 * Aufgabe: Parkhaus
 *
 * @author Fabian Gröger
 * @version 18.04.2018
 */
public class CarPark {
    private static final Logger LOG = LogManager.getLogger("CarPark");

    private final int id; // Identification of the car park
    private final int capacity; // capacity of the car park

    private int occupied; // how many spaces are occupied

    public CarPark(final int id, final int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public synchronized void enter(Car car) {
        if (occupied < capacity) {
            occupied++;
            // car enters
            if (ParkingSimulation.LOGGING) {
                LOG.info(car + " enters " + this);
            }
        } else {
            throw new IllegalStateException("Car parking is full");
        }
    }

    public synchronized void exit(Car car) {
        occupied--;
        // car leaves
        if (ParkingSimulation.LOGGING) {
            LOG.info(car + " left " + this);
        }
    }

    /**
     * Calculates how many parking spaces are free
     *
     * @return amount of free spaces
     */
    public synchronized int freeSpaces() {
        return capacity - occupied;
    }

    @Override
    public String toString() {
        return "CarPark[id= " + this.id + ", capacity= " + this.capacity + ", occupied= " + this.occupied + "]";
    }
}
