/*
 * Copyright 2018 Roland Gisler, HSLU Informatik, Switzerland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.AD.SW01.MemoryAllocator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Übung: Entwicklung Intro (E0)
 * Aufgabe: Wiedereinstieg in die Programmierung mit Java
 *
 * @author Fabian Gröger
 * @version 21.02.2018
 */
public final class MemoryDemo {

    private static final Logger LOG = LogManager.getLogger(MemoryDemo.class);

    /**
     * Privater Konstruktor.
     */
    private MemoryDemo() {
    }

    /**
     * Main-Methode.
     * @param args Startargumente.
     */
    public static void main(final String[] args) {
        final Memory memory = new MemorySimple(1024);
        LOG.info(memory);

        final Allocation block1 = memory.malloc(16);
        LOG.info(block1);

        LOG.info(memory);

        final Allocation block2 = memory.malloc(8);
        LOG.info(block2);

        LOG.info(memory);
        memory.free(block1);
    }
}
