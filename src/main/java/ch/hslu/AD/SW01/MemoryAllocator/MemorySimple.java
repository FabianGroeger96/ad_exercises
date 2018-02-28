package ch.hslu.AD.SW01.MemoryAllocator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Übung: Entwicklung Intro (E0)
 * Aufgabe: Wiedereinstieg in die Programmierung mit Java
 *
 * @author Fabian Gröger
 * @version 21.02.2018
 */
public class MemorySimple implements Memory {

    private Map<Integer, Integer> memory;

    private int size;
    private int allocated;

    public MemorySimple(int size) {
        memory = new HashMap<>();
        for (int i = 0; i < size; i++){
            memory.put(i, 0);
        }
    }

    /*
    Besser programmieren
     */
    private int countUsed(){
        int used = 0;
        Iterator iterator = memory.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry pair = (Map.Entry)iterator.next();
            if ((int)pair.getValue() == 1){
                used++;
            }
        }

        return used;
    }

    /*
    Besser programmieren
     */
    public void use(int size){
        int set = 0;
        Iterator iterator = memory.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry pair = (Map.Entry)iterator.next();
            if (set == size){
                return;
            } else if ((int)pair.getValue() == 0){
                pair.setValue(1);
                set++;
            }
        }
    }

    @Override
    public Allocation malloc(int blockSize) {
        //int used = this.countUsed();
        use(blockSize);
        return new Allocation(this.countUsed(), blockSize);
    }

    @Override
    public void free(Allocation allocation) {

    }

    @Override
    public int getAllocated() {
        return allocated;
    }

    @Override
    public int getFree() {
        return 0;
    }

    @Override
    public String toString() {
        int used = this.countUsed();
        return "MemorySimple[Belegt: " + used + "; Frei: " + (this.memory.size() - used) + "]";
    }
}
