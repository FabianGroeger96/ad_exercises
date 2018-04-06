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
 * Task macht nichts besonders, nur warten, bis er losgelassen wird.
 */
public class SimpleTask implements Runnable {

    private static final Logger LOG = LogManager.getLogger(SimpleTask.class);
    private final BadStartingLine line;
    private volatile Thread runThread;

    /**
     * Erzeugt eine einfache Aufgabe, die an einem Synchronisationobjekt wartet, bis weiterarbeiten
     * kann.
     *
     * @param line Synchronisationojekt.
     */
    public SimpleTask(final BadStartingLine line) {
        this.line = line;
    }

    @Override
    public void run() {
        runThread = Thread.currentThread();
        LOG.info(runThread.getName() + " halted...");
        line.halt();
        LOG.info(runThread.getName() + " released...");
    }

}
