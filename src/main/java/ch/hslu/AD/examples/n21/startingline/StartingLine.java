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
package ch.hslu.AD.examples.n21.startingline;

/**
 * »Startlinie«, an der Threads durch den Aufruf von halt gestoppt werden können.
 */
public class StartingLine {

    private boolean haltCondition = true;

    /**
     * Threads werden durch angehalten, wenn die Halt-Bedingnung zutrifft, sonst warten.
     *
     * @throws InterruptedException wenn das Warten unterbrochen wird.
     */
    public synchronized void halt() throws InterruptedException {
        while (this.haltCondition) {
            this.wait();
        }
    }

    /**
     * Alle wartenden Threads wecken.
     */
    public synchronized void go() {
        this.haltCondition = false;
        this.notifyAll();
    }
}
