package ch.hslu.AD.SW04.SimpleHashTable;

import java.util.Objects;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Einfache Hashtabelle
 *
 * Modell für einen HashItem. Immutable implementiert.
 *
 * @author Fabian Gröger
 * @version 14.03.2018
 */
public final class HashItem {

    private final int value;

    /**
     * Creates a HashItem with a value and a hashcode
     * @param value the value of the item
     */
    public HashItem(final int value) {
        this.value = value;
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HashItem)) {
            return false;
        }
        final HashItem alloc = (HashItem) other;
        return this.value == alloc.value;
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "HashItem[Value:" + this.value + "]";
    }
}
