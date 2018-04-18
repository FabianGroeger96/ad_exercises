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

import java.util.concurrent.atomic.AtomicLong;

/**
 * Beispiele von komplexeren Atomic-Operationen.
 */
public final class AtomicOperations {

    private static final AtomicLong VALUE = new AtomicLong();

    /**
     * Falsches Update, um Wert zu prüfen und falls Maximalwert, dann Speichern.
     *
     * @param newVal zu prüfender Wert.
     */
    public static void updateWrong(final long newVal) {
        VALUE.set(Math.max(VALUE.get(), newVal));
    }

    /**
     * Wert prüfen und falls Maximalwert, dann Speichern.
     *
     * @param newVal zu prüfender Wert.
     */
    public static void updateCorrect(final long newVal) {
        long alt, neu;
        do {
            alt = VALUE.get();
            neu = Math.max(alt, newVal);
        } while (VALUE.compareAndSet(alt, neu) == false);
    }

    /**
     * Wert prüfen und falls Maximalwert, dann Speichern. (Java 8 Version)
     *
     * @param newVal zu prüfender Wert.
     */
    public static void update(final long newVal) {
        VALUE.accumulateAndGet(newVal, Math::max);
    }

    /**
     * Addition mit aktuellem Wert.
     *
     * @param operand zu addierender Wert.
     * @return alter Wert.
     */
    public static long add(final long operand) {
        return VALUE.getAndAdd(operand);
    }

    /**
     * Multiplikation mit aktuellem Wert.
     *
     * @param operand zu multiplizierender Wert.
     * @return alter Wert.
     */
    public static long mult(final long operand) {
        return VALUE.getAndUpdate((long a) -> operand * a);
    }

    /**
     * Speicher einen neuen Atomic-Wert.
     *
     * @param newVal neuer Atomic-Wert.
     */
    public static void setValue(final long newVal) {
        VALUE.set(newVal);
    }

    /**
     * Liefert den Atomic-Wert.
     *
     * @return Atomic-Wert.
     */
    public static Long getValue() {
        return VALUE.get();
    }
}
