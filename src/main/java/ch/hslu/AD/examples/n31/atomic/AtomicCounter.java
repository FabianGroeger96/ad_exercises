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
package ch.hslu.AD.examples.n31.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread-sicherer Zähler mit AtomicInteger.
 */
public final class AtomicCounter {

    private final AtomicInteger counter = new AtomicInteger(0);

    /**
     * Zähler um 1 addieren.
     */
    public void increment() {
        counter.incrementAndGet();
    }

    /**
     * Zähler um 1 subtrahieren.
     */
    public void decrement() {
        counter.decrementAndGet();
    }

    /**
     * Liefert den Zählerstand.
     *
     * @return Zählerstand.
     */
    public int get() {
        return counter.get();
    }
}
