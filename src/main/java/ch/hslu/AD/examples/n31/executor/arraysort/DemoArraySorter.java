/*
 * Copyright 2018 Hochschule Luzern Informatik.
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
package ch.hslu.AD.examples.n31.executor.arraysort;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Demonstration mit einem Executor, der einen Sortier-Tasks ausführt.
 */
public final class DemoArraySorter {

    private static final Logger LOG = LogManager.getLogger(DemoArraySorter.class);

    /**
     * Privater Konstruktor.
     */
    private DemoArraySorter() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     * @throws InterruptedException wenn das Warten unterbrochen wird.
     * @throws ExecutionException   wenn bei der Thread-Ausführung ein Fehler
     *                              passiert.
     */
    public static void main(final String[] args) throws InterruptedException, ExecutionException {
        final byte[] array = new byte[16];
        new Random().nextBytes(array);
        final Callable<byte[]> callable = new ArraySorter(array);
        final ExecutorService executor = Executors.newCachedThreadPool();
        final Future<byte[]> result = executor.submit(callable);
        // ...hier was anderes machen
        final byte[] bs = result.get();
        LOG.info("lowest value  = " + bs[0]);
        LOG.info("highest value = " + bs[bs.length - 1]);
    }
}
