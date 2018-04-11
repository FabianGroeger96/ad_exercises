package ch.hslu.AD.SW06.Semaphore;

/**
 * Übung: Thread Steuerung (N2)
 * Aufgabe: Signalgeber
 *
 * @author Fabian Gröger
 * @version 11.04.2018
 */
public final class Semaphore {
    private int semaphore; // Semaphor Zähler
    private int limit;

    public Semaphore() {
        this.semaphore = 0;
        this.limit = 10;
    }

    public Semaphore(final int semaphore, final int limit) throws IllegalArgumentException {
        if (semaphore > limit) {
            throw new IllegalArgumentException("semaphore can not be greater than the limit");
        }
        this.semaphore = semaphore;
        this.limit = limit;
    }

    public synchronized int acquire() throws InterruptedException {
        while (semaphore == 0) {
            this.wait();
        }
        semaphore--;
        return semaphore;
    }

    public synchronized int acquire(int permits) throws InterruptedException {
        while (permits > 0) {
            while (semaphore == 0) {
                this.wait();
            }
            semaphore--;
            permits--;
        }
        return semaphore;
    }

    public synchronized int release() {
        if (semaphore == limit) {
            throw new IllegalStateException("can't release more than " + limit);
        }
        this.notifyAll();
        semaphore++;
        return semaphore;
    }

    public synchronized int release(int permits) {
        if (permits > limit) {
            throw new IllegalArgumentException("permits is greater than limit");
        }
        if (semaphore + permits > limit) {
            throw new IllegalStateException("can't release more than " + limit);
        }
        while (permits > 0) {
            this.notifyAll();
            semaphore++;
            permits--;
        }
        return semaphore;
    }
}
