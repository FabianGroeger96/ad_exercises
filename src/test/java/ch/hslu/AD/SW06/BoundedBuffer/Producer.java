package ch.hslu.AD.SW06.BoundedBuffer;

/**
 * Übung: Thread Steuerung (N2)
 * Aufgabe: Bounded Buffer
 *
 * @author Fabian Gröger
 * @version 11.04.2018
 */
public class Producer implements Runnable {

    private final int itemsToProduce;
    private final BoundedBuffer<String> buffer;
    private int produced = 0;

    public Producer(int itemsToProduce, BoundedBuffer<String> buffer) {
        this.itemsToProduce = itemsToProduce;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < itemsToProduce; i++) {
            String str = "String" + i;
            try {
                buffer.put(str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            produced++;
        }
    }

    public int getProduced() {
        return produced;
    }
}
