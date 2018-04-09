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
import ch.hslu.AD.examples.n21.buffer.Consumer;
import ch.hslu.AD.examples.n21.buffer.Producer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Demonstration des BoundedBuffers mit n Producer und m Consumer.
 */
public final class DemoPlainWorkerPool {

    private static final Logger LOG = LogManager.getLogger(DemoPlainWorkerPool.class);

    /**
     * Privater Konstruktor.
     */
    private DemoPlainWorkerPool() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     * @throws InterruptedException wenn das Warten unterbrochen wird.
     */
    public static void main(final String args[]) throws InterruptedException {
        final Random random = new Random();
        final PlainWorkerPool workerPool = new PlainWorkerPool(10, 5);
        final int nPros = 3;
        final Producer[] producers = new Producer[nPros];
        final int mCons = 2;
        final Consumer[] consumers = new Consumer[mCons];
        final BoundedBuffer<Integer> queue = new BoundedBuffer<>(50);
        for (int i = 0; i < nPros; i++) {
            producers[i] = new Producer(queue, random.nextInt(10000));
            workerPool.execute(producers[i]);
        }
        for (int i = 0; i < mCons; i++) {
            consumers[i] = new Consumer(queue);
            workerPool.execute(consumers[i]);
        }
        TimeUnit.MILLISECONDS.sleep(100);
        int sumPros = 0;
        for (int i = 0; i < nPros; i++) {
            LOG.info("Prod " + (char) (i + 65) + " = " + producers[i].getSum());
            sumPros += producers[i].getSum();
        }
        int sumCons = 0;
        for (int i = 0; i < mCons; i++) {
            LOG.info("Cons " + (char) (i + 65) + " = " + consumers[i].getSum());
            sumCons += consumers[i].getSum();
        }
        LOG.info(sumPros + " = " + sumCons);
    }
}
