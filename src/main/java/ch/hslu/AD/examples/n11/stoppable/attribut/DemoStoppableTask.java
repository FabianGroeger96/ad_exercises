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
package ch.hslu.AD.examples.n11.stoppable.attribut;

/**
 * Demostration des Stops einer nebenläufigen Aufgabe.
 */
public final class DemoStoppableTask {

    /**
     * Main-Demo.
     * @param args not used.
     * @throws InterruptedException wenn das Warten unterbrochen wird.
     */
    public static void main(String[] args) throws InterruptedException {
        final StoppableTask taskWithBoolean = new StoppableTask();
        Thread thread1 = new Thread(taskWithBoolean, "taskWithBoolean");
        thread1.start();
        Thread.sleep(200);
        taskWithBoolean.stopRequest();
    }
}
