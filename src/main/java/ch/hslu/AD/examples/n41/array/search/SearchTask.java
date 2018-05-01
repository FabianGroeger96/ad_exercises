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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Codebeispiel zu CountedCompleter für die Suche eines Elementes in einem
 * int-Array.
 */
@SuppressWarnings("serial")
public final class SearchTask extends CountedCompleter<Integer> {

    private static final Logger LOG = LogManager.getLogger(SearchTask.class);

    private static final int THRESHOLD = 5;
    private final int key;
    private final int[] array;
    private final int min;
    private final int max;
    private final AtomicInteger result;

    /**
     * Erzeugt einen Array-Such Task.
     *
     * @param key   zu findendes Element.
     * @param array Interger-Array.
     */
    public SearchTask(final int key, final int[] array) {
        this(null, key, array, 0, array.length, new AtomicInteger(-1));
    }

    private SearchTask(final CountedCompleter<?> parent, final int key, final int[] array, final int min, final int max,
                       final AtomicInteger result) {
        super(parent);
        this.key = key;
        this.array = array;
        this.min = min;
        this.max = max;
        this.result = result;
    }

    @Override
    public Integer getRawResult() {
        return result.get();
    }

    @Override
    public void compute() {
        if ((max - min) <= THRESHOLD) {
            for (int i = min; i < max; i++) {
                if (array[i] == key && result.compareAndSet(-1, i)) {
                    // LOG.debug("result = " + result.get());
                    this.quietlyCompleteRoot();
                    break;
                }
            }
        } else {
            final int mid = min + (max - min) / 2;
            this.addToPendingCount(2);
            final SearchTask taskLeft = new SearchTask(this, key, array, min, mid, result);
            taskLeft.fork();
            final SearchTask taskRight = new SearchTask(this, key, array, mid, max, result);
            taskRight.fork();
        }
        this.tryComplete();
    }
}
