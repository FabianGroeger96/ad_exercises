package ch.hslu.AD.SW04.CollisionHashTable;

import java.util.Objects;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Hashtabelle mit Kollisionen
 * <p>
 * Modell für einen HashItem. Immutable implementiert.
 *
 * @author Fabian Gröger
 * @version 14.03.2018
 */
public final class HashItem {

    private final int value;
    private final int hashCode;

    /**
     * Creates a HashItem with a value and a hashcode
     *
     * @param value the value of the item
     */
    public HashItem(final int value) {
        this.value = value;
        this.hashCode = 0;
    }

    public HashItem(final int value, final int hashCode) {
        this.value = value;
        this.hashCode = hashCode;
    }

    /**
     * Checks if the hash item is a tombstone
     *
     * @return if it's a tombstone return true, if not false
     */
    public boolean isTombstone() {
        if (this.value == -1 && this.hashCode == -1) {
            return true;
        } else {
            return false;
        }
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
        return this.value == alloc.value && this.hashCode == alloc.hashCode;
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        if (hashCode != 0) {
            return hashCode;
        } else {
            return Objects.hash(this.value);
        }
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        if (isTombstone()) {
            return "HashItem[Tombstone]";
        } else {
            return "HashItem[Value:" + this.value + "; Size:" + this.hashCode + "]";
        }
    }
}
