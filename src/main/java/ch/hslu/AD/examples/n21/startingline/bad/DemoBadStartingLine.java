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
package ch.hslu.AD.examples.n21.startingline.bad;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Demo einer Startlinie. Threads werden z.T. vorher interrupted.
 */
public class DemoBadStartingLine {

    private static final Logger LOG = LogManager.getLogger(DemoBadStartingLine.class);

    /**
     * Privater Konstruktor.
     */
    private DemoBadStartingLine() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     * @throws InterruptedException wenn Thread unterbrochen wird.
     */
    public static void main(final String args[]) throws InterruptedException {
        final BadStartingLine line = new BadStartingLine();
        final int nThreads = 3;
        final Thread[] threads = new Thread[nThreads];
        for (int i = 0; i < nThreads; i++) {
            threads[i] = new Thread(new SimpleTask(line), "Task " + (i + 1));
        }
        for (int i = 0; i < nThreads; i++) {
            threads[i].start();
            threads[i].interrupt();
        }
        Thread.sleep(1000);
        LOG.info("let's go...");
        line.go();
    }
}
