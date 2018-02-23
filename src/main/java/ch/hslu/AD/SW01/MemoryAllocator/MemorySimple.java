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
public class MemorySimple extends Memory {

    private Map<Integer, Integer> memory;

    public MemorySimple(int size) {
        memory = new HashMap<>();
        for (int i = 0; i < size; i++){
            memory.put(i, 0);
        }
    }

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
    public String toString() {
        int used = this.countUsed();
        return "MemorySimple[Belegt: " + used + "; Frei: " + (this.memory.size() - used) + "]";
    }

    @Override
    public Allocation malloc(int size) {
        //int used = this.countUsed();
        use(size);
        return new Allocation(this.countUsed(), size);
    }

    @Override
    public void free(Allocation allocation) {

    }
}
