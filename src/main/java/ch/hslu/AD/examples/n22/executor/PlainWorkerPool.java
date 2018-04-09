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
package ch.hslu.AD.examples.n22.executor;

import ch.hslu.AD.examples.n21.buffer.BoundedBuffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executor;

/**
 * Ein einfacher Executor, der Aufgaben an eine bestimmte Anzahl Worker zuteilt. Die Aufgaben werden
 * in einer Queue gespeichert.
 */
public final class PlainWorkerPool implements Executor {

    private static final Logger LOG = LogManager.getLogger(PlainWorkerPool.class);
    private final BoundedBuffer<Runnable> workQueue;
    private final int nWorkers;

    /**
     * Erzeugt einen einfacher Executor.
     *
     * @param capacity Grösse für die Anzahl Aufgaben.
     * @param nWorkers Anzahl Worker.
     */
    public PlainWorkerPool(final int capacity, final int nWorkers) {
        this.workQueue = new BoundedBuffer<>(capacity);
        this.nWorkers = nWorkers;
        for (int i = 0; i < this.nWorkers; ++i) {
            activate();
        }
    }

    /**
     * @see Executor
     */
    @Override
    public void execute(Runnable command) {
        try {
            workQueue.put(command);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void activate() {
        final Runnable runLoop = () -> {
            try {
                while (true) {
                    final Runnable r = workQueue.take();
                    r.run();
                }
            } catch (InterruptedException e) {
                LOG.debug(e);
            }
        };
        final Thread thread = new Thread(runLoop);
        thread.setDaemon(true);
        thread.start();
    }
}
