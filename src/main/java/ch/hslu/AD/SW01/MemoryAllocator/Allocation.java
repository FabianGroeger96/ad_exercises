package ch.hslu.AD.SW01.MemoryAllocator;

import java.util.Objects;

/**
 * Übung: Entwicklung Intro (E0)
 * Aufgabe: Wiedereinstieg in die Programmierung mit Java
 *
 * Modell für einen Speicherbereich. Immutable implementiert.
 *
 * @author Fabian Gröger
 * @version 21.02.2018
 */
public final class Allocation {

    private final int size;
    private final int address;

    /**
     * Erzeugt einen Block Addresse und Grösse.
     * @param address Startadresse.
     * @param size Grösse.
     */
    public Allocation(final int address, final int size) {
        this.address = address;
        this.size = size;
    }

    /**
     * Liefert die Startadresse.
     * @return Startadresse.
     */
    public int getAddress() {
        return this.address;
    }

    /**
     * Liefert die Grösse.
     * @return Grösse.
     */
    public int getSize() {
        return this.size;
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Allocation)) {
            return false;
        }
        final Allocation alloc = (Allocation) other;
        return this.address == alloc.address && this.size == alloc.size;
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.address, this.size);
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Allocation[Address:" + this.address + "; Size:" + this.size + "]";
    }
}
