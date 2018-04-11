package ch.hslu.AD.SW06.BoundedBuffer;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Übung: Thread Steuerung (N2)
 * Aufgabe: Bounded Buffer
 *
 * @author Fabian Gröger
 * @version 11.04.2018
 */
public class BoundedBuffer<T> {
    private int indexHead = 0;
    private int indexBottom = 0;
    private int maxSize;
    private int usedSize;
    private T[] buffer;

    private Semaphore putSemaphore;
    private Semaphore getSemaphore;

    /**
     * Default Konstruktor
     * Erstellt einen Ringbuffer mit max. Grösse 8
     */
    public BoundedBuffer() {
        this.maxSize = 8;
        buffer = (T[]) new Object[maxSize];

        this.getSemaphore = new Semaphore(0);
        this.putSemaphore = new Semaphore(8);
    }

    /**
     * Erstellt einen Ringbuffer mit übergebenen max. Grösse
     *
     * @param maxSize max. Grösse des Ringbuffers
     */
    public BoundedBuffer(final int maxSize) {
        this.maxSize = maxSize;
        buffer = (T[]) new Object[maxSize];

        this.getSemaphore = new Semaphore(0);
        this.putSemaphore = new Semaphore(maxSize);
    }

    /**
     * Überprüft ob der Buffer voll ist
     *
     * @return ob der Buffer voll ist
     */
    public boolean full() {
        return usedSize == maxSize;
    }

    /**
     * Überprüft ob der Buffer leer ist
     *
     * @return ob der Buffer leer ist
     */
    public boolean empty() {
        return usedSize == 0;
    }

    /**
     * Gibt die Anzahl Elemente im Buffer zurück
     *
     * @return Grösse des Buffers
     */
    public int size() {
        return usedSize;
    }

    public void put(final T element) throws InterruptedException {
        putSemaphore.acquire();
        enqueue(element);
        getSemaphore.release();
    }

    public void put(final T element, final long timeoutMills) throws InterruptedException {
        putSemaphore.tryAcquire(timeoutMills, TimeUnit.MILLISECONDS);
        enqueue(element);
        getSemaphore.release();
    }

    public T get() throws InterruptedException {
        getSemaphore.acquire();
        T element = dequeue();
        putSemaphore.release();
        return element;
    }

    public T get(final long timeoutMills) throws InterruptedException {
        getSemaphore.tryAcquire(timeoutMills, TimeUnit.MILLISECONDS);
        T element = dequeue();
        putSemaphore.release();
        return element;
    }

    /**
     * Anhängen eines Elements am Ende
     *
     * @param element Element zum anhängen
     * @return ob der Vorgang geklappt hat
     */
    public synchronized boolean enqueue(final T element) {
        if (full()) { // Wenn der Buffer voll ist, können wir nichts anhängen, weil wir nichts überschrieben
            throw new BufferOverflowException();
        }
        buffer[this.indexBottom] = element;
        this.indexBottom = (this.indexBottom + 1) % this.buffer.length;
        this.usedSize++;
        return true;
    }

    /**
     * Entnehmen eines Elements am Anfang
     *
     * @return das entnommene Element
     */
    public synchronized T dequeue() {
        if (empty()) { // Wenn der Buffer leer ist, kann nichts ausgelesen werden
            throw new BufferUnderflowException();
        }

        T element = buffer[this.indexHead];
        buffer[this.indexHead] = null;

        this.indexHead = (this.indexHead + 1) % this.buffer.length;
        this.usedSize--;

        return element;
    }

    /**
     * Löscht den ganzen RingBuffer
     */
    public void clear() {
        for (int i = 0; i < buffer.length; i++) {
            this.buffer[i] = null;
        }
        this.indexHead = 0;
        this.indexBottom = 0;
        this.usedSize = 0;
    }

    /**
     * Liefert die String Representation der Klasse
     *
     * @return die String Representation
     */
    @Override
    public String toString() {
        return "BoundedBuffer[indexHead=" + this.indexHead + ", indexBottom=" + this.indexBottom + ", Used Size=" + this.usedSize + ", Max. Size=" + this.maxSize + "]";
    }
}
