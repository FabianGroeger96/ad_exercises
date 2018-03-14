package ch.hslu.AD.SW04.CollisionHashTable;

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
    private final int hashCode;

    /**
     * Creates a HashItem with a value and a hashcode
     * @param value the value of the item
     * @param hashCode the hashcode of the item
     */
    public HashItem(final int value, final int hashCode) {
        this.value = value;
        this.hashCode = hashCode;
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
        return this.hashCode;
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "HashItem[Value:" + this.value + "; HashCode:" + this.hashCode + "]";
    }
}
