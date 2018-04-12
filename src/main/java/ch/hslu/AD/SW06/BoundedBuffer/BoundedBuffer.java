package ch.hslu.AD.SW06.BoundedBuffer;

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

    /**
     * Anhängen eines Elements am Ende
     *
     * @param element Element zum anhängen
     * @return ob der Vorgang geklappt hat
     */
    public synchronized void put(final T element) throws InterruptedException {
        while (full()) {
            System.out.println("buffer is full, put() has to wait");
            wait();
        }

        usedSize++;
        buffer[indexBottom] = element;
        indexBottom = (indexBottom + 1) % buffer.length;

        if (usedSize == 1) {
            System.out.println("buffer is not empty any longer, get() can continue");
            notifyAll();
        }
    }

    /**
     * Entnehmen eines Elements am Anfang
     *
     * @return das entnommene Element
     */
    public synchronized T get() throws InterruptedException {
        while (empty()) {
            System.out.println("buffer is empty, get() has to wait");
            wait();
        }

        usedSize--;
        T obj = (T) buffer[indexHead];
        buffer[indexHead] = null;
        indexHead = (indexHead + 1) % buffer.length;

        if (usedSize == buffer.length - 1) {
            notifyAll();
        }

        return obj;
    }

    /**
     * Entnehmen eines Elements am Anfang mit TimeOut
     *
     * @param timeoutMills TimeOut in milli Sekunden
     * @return das entnommene Element
     * @throws InterruptedException
     */
    public synchronized T get(final long timeoutMills) throws InterruptedException {
        while (empty()) {
            System.out.println("buffer is empty, get(int millis) has to wait");
            wait(timeoutMills);
        }

        usedSize--;
        T obj = (T) buffer[indexHead];
        buffer[indexHead] = null;
        indexHead = (indexHead + 1) % buffer.length;

        if (usedSize == buffer.length - 1) {
            notifyAll();
        }

        return obj;
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
