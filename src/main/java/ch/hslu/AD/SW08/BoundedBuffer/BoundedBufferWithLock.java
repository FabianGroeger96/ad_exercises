package ch.hslu.AD.SW08.BoundedBuffer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Übung: Weiterführende Konzepte (N3)
 * Aufgabe: Bounded Buffer
 *
 * @author Fabian Gröger
 * @version 18.04.2018
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
     * Anhängen eines Elements am Ende
     *
     * @param element Element zum anhängen
     * @return ob der Vorgang geklappt hat
     * @throws InterruptedException
     */
    public void put(final T element) throws InterruptedException {
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
     * Anhängen eines Elements am Ende mit TimeOut
     *
     * @param element      Element zum anhängen
     * @param timeoutMills TimeOut in milli Sekunden
     * @throws InterruptedException
     */
    public void put(final T element, final long timeoutMills) throws InterruptedException {
        lock.lock();
        try {
            while (count == data.length) {
                notFull.await(timeoutMills, TimeUnit.MILLISECONDS);
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
     * @throws InterruptedException
     */
    public T get() throws InterruptedException {
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
    public T get(final long timeoutMills) throws InterruptedException {
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
     * Liefert das erste Element im Buffer zurück
     *
     * @return erste Element im Buffer
     * @throws InterruptedException
     */
    public T front() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            count--;
            T obj = (T) data[tail];
            data[tail] = null;
            tail = (tail + 1) % data.length;
            if (count == data.length - 1) {
                notFull.signalAll();
            }
            return obj;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Liefert das letzte Element im Buffer zurück
     *
     * @return letzte Element im Buffer
     * @throws InterruptedException
     */
    public T back() throws InterruptedException {
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
     * Fü̈gt ein neues Element nach dem LIFO Prinzip in den Buffer ein
     *
     * @param element Element um einzufügen
     * @throws InterruptedException
     */
    public void push(final T element) throws InterruptedException {
        lock.lock();
        try {
            while (count == data.length) {
                notFull.await();
            }
            count++;
            data[head] = element;
            head = (head + 1) % data.length;
            if (count == 1) {
                notEmpty.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Entfernt ein Element nach dem LIFO Prinzip aus dem Buffer
     *
     * @return Element nach LIFO
     * @throws InterruptedException
     */
    public T pop() throws InterruptedException {
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
     * Liefert die String Representation der Klasse
     *
     * @return die String Representation
     */
    @Override
    public String toString() {
        return "BoundedBuffer[head=" + this.head + ", tail=" + this.tail + ", Used Size=" + this.count + ", Max. Size=" + this.data.length + "]";
    }
}
