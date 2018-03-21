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
package ch.hslu.AD.examples.n12.workitem;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Eine Work Queue, der Aufgaben übergeben werden und deren Bearbeitung abstrahiert wird. Die
 * Schnittstelle processItem muss für eigene Zwecke überschrieben werden. Die Klasse ist
 * thread-sicher.
 */
public abstract class AbstractWorkQueue {

    private final BlockingQueue<Object> queue;
    private boolean stopped = false;

    /**
     * Erzeugt die Work Queue.
     */
    public AbstractWorkQueue() {
        queue = new LinkedBlockingDeque<>();
    }

    /**
     * Aufgabe der Work Queue übergeben.
     * @param workItem Aufgabe.
     */
    public final void enqueue(final Object workItem) {
        synchronized (queue) {
            queue.add(workItem);
        }
    }

    /**
     * Einen Worker nebenläufig starten.
     */
    public void startWorker() {
        new Thread(new Worker()).start();
    }

    /**
     * Worker stoppen.
     */
    public final void stopWorker() {
        synchronized (queue) {
            stopped = true;
        }
    }

    /**
     * Abstrahierte Bearbeitung eines Work Items.
     * @param workItem Work Item.
     * @throws InterruptedException wenn das Warten an der Queue unterbrochen wird.
     */
    protected abstract void processItem(final Object workItem) throws InterruptedException;

    private class Worker implements Runnable {

        @Override
        public void run() {
            while (true) { // Hauptschleife
                Object workItem;
                synchronized (queue) {
                    if (stopped) {
                        return;
                    }
                    try {
                        workItem = queue.take();
                    } catch (InterruptedException ie) {
                        return;
                    }
                    try {
                        processItem(workItem);
                    } catch (InterruptedException ie) {
                        return;
                    }
                }
            }
        }
    }
}
