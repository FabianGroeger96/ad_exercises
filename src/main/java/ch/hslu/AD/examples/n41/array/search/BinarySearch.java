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

/**
 * The {@code BinarySearch} class provides a static method for binary searching
 * for an integer in a sorted array of integers.
 */
public final class BinarySearch {

    /**
     * This class should not be instantiated.
     */
    private BinarySearch() {
    }

    /**
     * Returns the index of the specified key in the specified array.
     *
     * @param array the array of integers, must be sorted in ascending order.
     * @param key   the search key.
     * @return index of key in array if present; {@code -1} otherwise.
     */
    public static int indexOf(final int[] array, final int key) {
        int min = 0;
        int max = array.length - 1;
        while (min <= max) {
            int mid = min + (max - min) / 2;
            if (key < array[mid]) {
                max = mid - 1;
            } else if (key > array[mid]) {
                min = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
