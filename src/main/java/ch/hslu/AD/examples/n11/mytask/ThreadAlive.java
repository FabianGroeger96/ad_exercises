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
package ch.hslu.AD.examples.n11.mytask;

/**
 * Zeichen-Ausgabe auf gemeinsame Ressource Console.
 */
public final class ThreadAlive {

    /**
     * Main-Demo.
     * @param args not used.
     * @throws InterruptedException wenn das Warten unterbrochen wird.
     */
    public static void main(final String[] args) throws InterruptedException {
        final MyTask myTask = new MyTask();
        final Thread thread = new Thread(myTask, "MyTask-Thread");
        thread.start();
        while (thread.isAlive()) {
            Thread.sleep(10);
        }
        for (int i = 0; i < 1000; i++) {
            System.out.print("x");
        }
    }
}
