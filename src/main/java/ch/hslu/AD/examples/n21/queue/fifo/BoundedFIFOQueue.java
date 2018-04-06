/*
 * Copyright 2018 Hochschule Luzern - Informatik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.AD.examples.n21.queue.fifo;

/**
 * FIFO-Puffer (First In First Out) mit einer begrenzten Kapazität. Der FIFO-Puffer ist thread
 * sicher.
 *
 * @param <T> Element Typ.
 */
public final class BoundedFIFOQueue<T> {

    private final T[] data;
    private int head;
    private int tail;
    private int count;

    /**
     * Erzeugt einen FIFO-Puffer mit bestimmter Kapazität.
     *
     * @param cap Kapazität des FIFO-Puffers.
     */
    @SuppressWarnings("unchecked")
    public BoundedFIFOQueue(int cap) {
        this.data = (T[]) new Object[cap];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    /**
     * Ein Element T speichern. Falls der Puffer voll ist, warten bis ein Platz frei wird.
     *
     * @param elem zu speicherndes Element.
     * @throws InterruptedException wenn das Warten unterbrochen wird.
     */
    public synchronized void put(final T elem) throws InterruptedException {
        while (count == data.length) {
            this.wait();
        }
        count++;
        data[tail] = elem;
        tail = (tail + 1) % data.length;
        if (count == 1) {
            this.notifyAll();
        }
    }

    /**
     * Ein Element T speichern oder nach einem Timeout abbrechen. Falls der Puffer voll ist, warten
     * bis ein Platz frei wird.
     *
     * @param elem   zu speicherndes Element.
     * @param millis Timeout bis zum Abbruch.
     * @return true, wenn Element gespeichert wurde, false, wenn Timeout eingetreten ist.
     * @throws InterruptedException wenn das Warten unterbrochen wird.
     */
    public synchronized boolean put(final T elem, final long millis) throws InterruptedException {
        while (count == data.length) {
            this.wait(millis);
            if (count == data.length) {
                return false;
            }
        }
        count++;
        data[tail] = elem;
        tail = (tail + 1) % data.length;
        if (count == 1) {
            this.notifyAll();
        }
        return true;
    }

    /**
     * Ein Element T auslesen. Falls der Puffer leer ist, warten bis ein Platz belegt ist.
     *
     * @return ausgelesenes Element.
     * @throws InterruptedException wenn das Warten unterbrochen wird.
     */
    public synchronized T get() throws InterruptedException {
        while (count == 0) {
            this.wait();
        }
        count--;
        T obj = data[head];
        data[head] = null;
        head = (head + 1) % data.length;
        if (count == data.length - 1) {
            this.notifyAll();
        }
        return obj;
    }
}
