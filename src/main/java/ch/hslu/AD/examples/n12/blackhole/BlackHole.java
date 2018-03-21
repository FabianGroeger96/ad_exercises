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
package ch.hslu.AD.examples.n12.blackhole;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Schwarzes Loch zur Demonstration eines typischen Deadlock.
 */
public final class BlackHole {

    private final BlockingQueue<String> queue;

    /**
     * Erzeugt ein Schwarzes Loch mit einer Blocking-Double-Endet-Queue.
     */
    public BlackHole() {
        queue = new LinkedBlockingDeque<>();
    }

    /**
     * Wirft etwas ins Schawrze Loch hinein.
     * @param thing kann alles sein, solange es ein String ist
     */
    public void put(final String thing) {
        synchronized (queue) {
            queue.add(thing);
        }
    }

    /**
     * Holt etwas aus dem Schawrzen Loch.
     * @return irgend etwas, solange es ein String ist
     * @throws InterruptedException Interrupted.
     */
    public String get() throws InterruptedException {
        synchronized (queue) {
            return queue.take();
        }
    }
}
