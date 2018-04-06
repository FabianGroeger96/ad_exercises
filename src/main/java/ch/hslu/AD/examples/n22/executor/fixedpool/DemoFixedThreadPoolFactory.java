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
package ch.hslu.AD.examples.n22.executor.fixedpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Demo eines FixedThreadPool Executors mit eingener ThreadFactory.
 */
public final class DemoFixedThreadPoolFactory {

    private static final Logger LOG = LogManager.getLogger(DemoFixedThreadPoolFactory.class);

    /**
     * Privater Konstruktor.
     */
    private DemoFixedThreadPoolFactory() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        final ExecutorService executor;
        final int nWorker = 3;
        executor = Executors.newFixedThreadPool(nWorker, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                final Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });
        for (int nTask = 1; nTask <= 5; nTask++) {
            final int maxRange = nTask * 1000;
            executor.execute(() -> {
                final Thread runThread = Thread.currentThread();
                LOG.info(runThread.getName() + " starts ");
                long sum = 0;
                for (int i = 1; i <= maxRange; i++) {
                    sum += i;
                }
                LOG.info(runThread.getName() + " finished sum(" + maxRange + ") = " + sum);
            });
        }
        LOG.info("main finished");
    }
}
