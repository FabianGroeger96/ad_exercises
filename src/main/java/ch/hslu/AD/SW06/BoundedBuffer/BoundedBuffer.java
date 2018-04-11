package ch.hslu.AD.SW06.BoundedBuffer;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

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

    /**
     * Default Konstruktor
     * Erstellt einen Ringbuffer mit max. Grösse 8
     */
    public BoundedBuffer() {
        this.maxSize = 8;
        buffer = (T[]) new Object[maxSize];
    }

    /**
     * Erstellt einen Ringbuffer mit übergebenen max. Grösse
     *
     * @param maxSize max. Grösse des Ringbuffers
     */
    public BoundedBuffer(final int maxSize) {
        this.maxSize = maxSize;
        buffer = (T[]) new Object[maxSize];
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

    public synchronized void put(final T element) throws InterruptedException {
        while (full()) {
            System.out.println("buffer is full, put() has to wait");
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.err.println("interrupted: " + e.getMessage());
            }
        }
        boolean wasEmpty = empty();
        putElement(element);
        if (wasEmpty) {
            System.out.println("buffer is not empty any longer, get() can continue");
            this.notifyAll();
        }
    }

    public synchronized T get() throws InterruptedException {
        while (empty()) {
            System.out.println("buffer is empty, get() has to wait");
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.err.println("interrupted: " + e.getMessage());
            }
        }
        return getElement();
    }

    public synchronized T get(final long timeoutMills) throws InterruptedException {
        while (empty()) {
            System.out.println("buffer is empty, get(int millis) has to wait");
            try {
                this.wait(timeoutMills);
            } catch (InterruptedException e) {
                System.err.println("interrupted: " + e.getMessage());
            }
        }
        return getElement();
    }

    /**
     * Anhängen eines Elements am Ende
     *
     * @param element Element zum anhängen
     * @return ob der Vorgang geklappt hat
     */
    public synchronized boolean putElement(final T element) {
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
    public synchronized T getElement() {
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
