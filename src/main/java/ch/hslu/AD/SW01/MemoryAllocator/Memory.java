package ch.hslu.AD.SW01.MemoryAllocator;

/**
 * Übung: Entwicklung Intro (E0)
 * Aufgabe: Wiedereinstieg in die Programmierung mit Java
 *
 * @author Fabian Gröger
 * @version 21.02.2018
 */
public abstract class Memory {

    public abstract Allocation malloc(int size);

    public abstract void free(Allocation allocation);

}
