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
package ch.hslu.AD.examples.n11.stoppable.attribut;

import java.math.BigInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Nebenläufige Aufgabe, die gestoppt werden kann.
 */
public final class StoppableTask implements Runnable {

    private static final Logger LOG = LogManager.getLogger(StoppableTask.class);
    private volatile Thread runThread;
    private volatile boolean isStopped = false;

    /**
     * Stoppen der nebenläufigen Aufgabe.
     */
    public void stopRequest() {
        isStopped = true;
        if (runThread != null) {
            runThread.interrupt();
        }
    }

    /**
     * Liefert, ob die nebenläufige Aufgabe gestoppt ist.
     * @return true wenn die nebenläufige Aufgabe gestoppt ist, sonst false.
     */
    public boolean isStopped() {
        return isStopped;
    }

    @Override
    public void run() {
        runThread = Thread.currentThread();
        // Initialisierungsphase
        final int max = 10000;
        int n = 0;
        while (n < max && !isStopped()) {
            // Arbeitsphase
            BigInteger bigInteger = new BigInteger(Integer.toString(n));
            if (bigInteger.isProbablePrime(Integer.MAX_VALUE)) {
                System.out.print(".");
            }
            n++;
        }
        // Aufräumphase
        System.out.println();
        if (max == n) {
            LOG.info("finished: " + runThread.getName());
        } else {
            LOG.info("stopped: " + runThread.getName());
        }
    }
}
