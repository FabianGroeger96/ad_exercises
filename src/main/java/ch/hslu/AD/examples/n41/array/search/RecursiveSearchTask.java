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
package ch.hslu.AD.examples.n41.array.search;

import java.util.concurrent.RecursiveTask;

/**
 * Codebeispiel zu RecursiveTask für die Suche eines Elementes in einem
 * int-Array.
 */
@SuppressWarnings("serial")
public final class RecursiveSearchTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 4;
    private final int key;
    private final int[] array;
    private final int min;
    private final int max;

    /**
     * Erzeugt einen Array-Such Task.
     *
     * @param key   zu findendes Element.
     * @param array Interger-Array.
     */
    public RecursiveSearchTask(final int key, final int[] array) {
        this(key, array, 0, array.length - 1);
    }

    private RecursiveSearchTask(final int key, final int[] array, final int min, final int max) {
        this.key = key;
        this.array = array;
        this.min = min;
        this.max = max;
    }

    @Override
    protected Integer compute() {
        if ((max - min) <= THRESHOLD) {
            for (int i = min; i <= max; i++) {
                if (array[i] == key) {
                    return i;
                }
            }
        } else {
            final int mid = min + (max - min) / 2;
            final RecursiveSearchTask searchLeft = new RecursiveSearchTask(key, array, min, mid);
            searchLeft.fork();
            final RecursiveSearchTask searchRight = new RecursiveSearchTask(key, array, mid, max);
            final int indexRight = searchRight.invoke();
            final int indexLeft = searchLeft.join();
            if (indexRight >= 0) {
                return indexRight;
            }
            if (indexLeft >= 0) {
                return indexLeft;
            }
        }
        return -1;
    }
}
