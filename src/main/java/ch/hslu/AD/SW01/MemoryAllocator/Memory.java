package ch.hslu.AD.SW01.MemoryAllocator;

/**
 * Übung: Entwicklung Intro (E0)
 * Aufgabe: Wiedereinstieg in die Programmierung mit Java
 *
 * @author Fabian Gröger
 * @version 21.02.2018
 */
public interface Memory {

    Allocation malloc(int size);

    void free(Allocation allocation);

    int getAllocated();

    int getFree();
}
