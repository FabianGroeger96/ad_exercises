package ch.hslu.AD.SW02.ArrayRingBuffer;

/**
 * Übung: Arrays, Listen, Stack und Queue (D1)
 * Aufgabe: Implementation einer Queue mit Array als Ringbuffer
 *
 * @author Fabian Gröger
 * @version 07.03.2018
 */
public interface RingBufferInterface<E> {

    /**
     * Überprüft ob der RingBuffer voll ist
     *
     * @return ob es voll ist
     */
    public boolean isFull();

    /**
     * Überprüft ob der RingBuffer leer ist
     *
     * @return ob es leer ist
     */
    public boolean isEmpty();

    /**
     * Anhängen eines Elements am Ende
     *
     * @return ob der Vorgang geklappt hat
     */
    public boolean enqueue(E element);

    /**
     * Entnehmen eines Elements am Anfang
     *
     * @return gibt das Element zurück
     */
    public E dequeue();

    /**
     * Löscht den ganzen RingBuffer
     */
    public void clear();

}
