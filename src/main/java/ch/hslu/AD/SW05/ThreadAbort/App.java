package ch.hslu.AD.SW05.ThreadAbort;

import java.util.ArrayList;
import java.util.List;

/**
 * Übung: Threads & Synchronisation (N1)
 * Aufgabe: Das Ende eines Threads
 *
 * @author Fabian Gröger
 * @version 26.03.2018
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        List<AdditionTask> tasks = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            AdditionTask task = new AdditionTask(i * 10, i * 100);
            tasks.add(task);
            threads.add(new Thread(task, "Task " + i));
        }

        threads.stream().forEach((t) -> t.start());
        Thread.sleep(1000);
        tasks.stream().forEach((t) -> t.stop());
    }
}
