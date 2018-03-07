package ch.hslu.AD.SW02.ArrayRingBuffer;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

/**
 * Übung: Arrays, Listen, Stack und Queue (D1)
 * Aufgabe: Implementation einer Queue mit Array als Ringbuffer
 *
 * @author Fabian Gröger
 * @version 07.03.2018
 */
public class RingBuffer<T> implements RingBufferInterface<T> {

    private int indexHead = 0;
    private int indexBottom = 0;
    private int maxSize;
    private int usedSize;
    private T[] buffer;

    /**
     * Default Konstruktor
     * Erstellt einen Ringbuffer mit max. Grösse 8
     */
    public RingBuffer() {
        this.maxSize = 8;
        buffer = (T[]) new Object[maxSize];
    }

    /**
     * Erstellt einen Ringbuffer mit übergebenen max. Grösse
     *
     * @param maxSize max. Grösse des Ringbuffers
     */
    public RingBuffer(final int maxSize) {
        this.maxSize = maxSize;
        buffer = (T[]) new Object[maxSize];
    }

    /**
     * Überprüft ob der Buffer voll ist
     *
     * @return ob der Buffer voll ist
     */
    @Override
    public boolean isFull() {
        return usedSize == buffer.length;
    }

    /**
     * Überprüft ob der Buffer leer ist
     *
     * @return ob der Buffer leer ist
     */
    @Override
    public boolean isEmpty() {
        return usedSize == 0;
    }

    /**
     * Gibt die Grösse des Buffers zurück
     *
     * @return Grösse des Buffers
     */
    public int size() {
        return buffer.length;
    }

    /**
     * Gibt die Anzahl benutzter Felder zurück
     *
     * @return benutzte Felder
     */
    public int used() {
        return usedSize;
    }

    /**
     * Anhängen eines Elements am Ende
     *
     * @param element Element zum anhängen
     * @return ob der Vorgang geklappt hat
     */
    @Override
    public boolean enqueue(final T element) {
        if (isFull()) { // Wenn der Buffer voll ist, können wir nichts anhängen, weil wir nichts überschrieben
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
    @Override
    public T dequeue() {
        if (isEmpty()) { // Wenn der Buffer leer ist, kann nichts ausgelesen werden
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
    @Override
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
        return "RingBuffer[indexHead=" + this.indexHead + ", indexBottom=" + this.indexBottom + ", Used Size=" + this.usedSize + ", Max. Size=" + this.maxSize + "]";
    }
}
