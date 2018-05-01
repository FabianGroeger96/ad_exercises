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
package ch.hslu.AD.examples.n41.array;

import ch.hslu.AD.examples.n41.array.init.RandomInitTask;
import ch.hslu.AD.examples.n41.array.search.BinarySearch;
import ch.hslu.AD.examples.n41.array.search.RecursiveSearchTask;
import ch.hslu.AD.examples.n41.array.search.SearchTask;
import ch.hslu.AD.examples.n41.array.sort.SortTask;
import ch.hslu.AD.examples.n41.array.sort.check.SortCheckTask;
import ch.hslu.AD.examples.n41.array.sum.SumTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

/**
 * Codebeispiel für die Verwendung von verschiedenen Recursive-Klassen mit einem
 * Fork-Join-Pool.
 */
public final class DemoArrayActions {

    private static final Logger LOG = LogManager.getLogger(DemoArrayActions.class);

    /**
     * Privater Konstruktor.
     */
    private DemoArrayActions() {
    }

    private static void printItem(final String info, final int key, final int index) {
        if (index >= 0) {
            LOG.info(info + "key(" + key + ") found at " + index);
        } else {
            LOG.info(info + "key(" + key + ") not found");
        }
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
        final int size = 17;
        final int[] array = new int[size];
        final ForkJoinPool pool = new ForkJoinPool();
        final RandomInitTask initTask = new RandomInitTask(array, 100);
        pool.invoke(initTask);
        LOG.info("Random Init.   : " + Arrays.toString(array));
        SumTask sumTask = new SumTask(array);
        long result = pool.invoke(sumTask);
        LOG.info("Init. Checksum : " + result);
        final int key = array[size - 1];
        final RecursiveSearchTask recSearchTask = new RecursiveSearchTask(key, array);
        printItem("Recurs. Search : ", key, pool.invoke(recSearchTask));
        final SearchTask searchTask = new SearchTask(key, array);
        printItem("Counted Search : ", key, pool.invoke(searchTask));
        final SortTask sortTask = new SortTask(array);
        pool.invoke(sortTask);
        LOG.info("Merge Sort     : " + Arrays.toString(array));
        sumTask = new SumTask(array);
        result = pool.invoke(sumTask);
        LOG.info("Sort Checksum  : " + result);
        final SortCheckTask check = new SortCheckTask(array);
        boolean ok = pool.invoke(check);
        LOG.info("Sort Check     : " + ok);
        printItem("Binary Search  : ", key, BinarySearch.indexOf(array, key));
    }
}
