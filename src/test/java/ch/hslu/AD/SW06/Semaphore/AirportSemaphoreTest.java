package ch.hslu.AD.SW06.Semaphore;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Übung: Thread Steuerung (N2)
 * Aufgabe: Signalgeber
 *
 * @author Fabian Gröger
 * @version 11.04.2018
 */
public class AirportSemaphoreTest {

    @Test
    public void airportSemaphore() {
        final int nRunways = 2;
        final int nAirplanes = 10;
        Semaphore runwaySemaphore = new Semaphore(nRunways, nRunways);

        List<Thread> airplanes = new ArrayList<>(nAirplanes);
        for (int n = 0; n < nAirplanes; n++) {
            final int number = n;
            airplanes.add(new Thread(() -> {
                String name = "Airplane-" + number;
                System.out.println(name + " wants to land");

                try {
                    runwaySemaphore.acquire();
                } catch (InterruptedException e) {
                    System.err.println(name + ": " + e.getMessage());
                }

                System.out.println("Runway is free for " + name);
                System.out.println(name + " is landing");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.err.println(name + ": " + e.getMessage());
                }

                runwaySemaphore.release();
                System.out.println(name + " has left the runway");
            }));
        }

        for (Thread t : airplanes) {
            t.start();
        }

        for (Thread t : airplanes) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.err.println(t.getName() + ": " + e.getMessage());
            }
        }

        Assert.assertEquals(nRunways, runwaySemaphore.getFree());
    }

}