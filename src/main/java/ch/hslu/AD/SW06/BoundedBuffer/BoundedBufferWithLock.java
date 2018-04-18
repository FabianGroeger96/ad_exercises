package ch.hslu.AD.SW06.BoundedBuffer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Übung: Thread Steuerung (N2)
 * Aufgabe: Bounded Buffer
 *
 * @author Fabian Gröger
 * @version 11.04.2018
 */
public class BoundedBufferWithLock<T> {
    private T[] data;
    private int head;
    private int tail;
    private int count;

    // Um den BoundedBuffer zu schützen wird ein Lock-Object verwendet
    private final Lock lock = new ReentrantLock();

    // Bedingungsvariablen für Schreiben und Lesen
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    /**
     * Default Konstruktor
     * Erstellt einen Ringbuffer mit max. Grösse 8
     */
    public BoundedBufferWithLock() {
        data = (T[]) new Object[8];
    }

    /**
     * Erstellt einen Ringbuffer mit übergebenen max. Grösse
     *
     * @param cap max. Grösse des Ringbuffers
     */
    public BoundedBufferWithLock(final int cap) {
        data = (T[]) new Object[cap];
        head = 0;
        tail = 0;
        count = 0;
    }

    /**
     * Überprüft ob der Buffer voll ist
     *
     * @return ob der Buffer voll ist
     */
    public boolean full() {
        return count == data.length;
    }

    /**
     * Überprüft ob der Buffer leer ist
     *
     * @return ob der Buffer leer ist
     */
    public boolean empty() {
        return count == 0;
    }

    /**
     * Gibt die Anzahl Elemente im Buffer zurück
     *
     * @return Grösse des Buffers
     */
    public int size() {
        return count;
    }

    /**
     * Anhängen eines Elements am Ende
     *
     * @param element Element zum anhängen
     * @return ob der Vorgang geklappt hat
     */
    public synchronized void put(final T element) throws InterruptedException {
        lock.lock();
        try {
            while (count == data.length) {
                notFull.await();
            }
            count++;
            data[tail] = element;
            tail = (tail + 1) % data.length;
            if (count == 1) {
                notEmpty.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Entnehmen eines Elements am Anfang
     *
     * @return das entnommene Element
     */
    public synchronized T get() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            count--;
            T obj = (T) data[head];
            data[head] = null;
            head = (head + 1) % data.length;
            if (count == data.length - 1) {
                notFull.signalAll();
            }
            return obj;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Entnehmen eines Elements am Anfang mit TimeOut
     *
     * @param timeoutMills TimeOut in milli Sekunden
     * @return das entnommene Element
     * @throws InterruptedException
     */
    public synchronized T get(final long timeoutMills) throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await(timeoutMills, TimeUnit.MILLISECONDS);
            }
            count--;
            T obj = (T) data[head];
            data[head] = null;
            head = (head + 1) % data.length;
            if (count == data.length - 1) {
                notFull.signalAll();
            }
            return obj;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Löscht den ganzen RingBuffer
     */
    public void clear() {
        for (int i = 0; i < data.length; i++) {
            this.data[i] = null;
        }
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    /**
     * Liefert die String Representation der Klasse
     *
     * @return die String Representation
     */
    @Override
    public String toString() {
        return "BoundedBuffer[head=" + this.head + ", tail=" + this.tail + ", Used Size=" + this.count + ", Max. Size=" + this.data.length + "]";
    }
}
