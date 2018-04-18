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

import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * Nebenläufige Sortieren eines Arrays.
 */
public final class ArraySorter implements Callable<byte[]> {

    private final byte[] array;

    /**
     * Erzeugt ein Array-Sorter
     *
     * @param array zu sortierendes Array
     */
    public ArraySorter(final byte[] array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    @Override
    public byte[] call() {
        Arrays.sort(array);
        return array;
    }
}
