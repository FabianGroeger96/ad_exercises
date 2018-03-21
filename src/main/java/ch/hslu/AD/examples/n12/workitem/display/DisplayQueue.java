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
package ch.hslu.AD.examples.n12.workitem.display;

import ch.hslu.AD.examples.n12.workitem.AbstractWorkQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Anzeige von Work Items.
 */
public final class DisplayQueue extends AbstractWorkQueue {

    private static final Logger LOG = LogManager.getLogger(DisplayQueue.class);

    /**
     * Anzeige eines Work Items für eine Sekunde.
     *
     * @param workItem Work Item.
     * @throws InterruptedException wird hier nicht passieren.
     */
    @Override
    protected void processItem(final Object workItem) throws InterruptedException {
        LOG.info(workItem);
        Thread.sleep(1000);
    }
}
