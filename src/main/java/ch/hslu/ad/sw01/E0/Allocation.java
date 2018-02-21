package ch.hslu.ad.sw01.E0;

import java.util.Objects;

public final class Allocation implements Comparable<Allocation>{

    private int startaddress;
    private int size;

    public Allocation(int startaddress, int size) {
        this.startaddress = startaddress;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Allocation[Address: " + this.startaddress + "; Size: " + this.size + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Allocation)) {
            return false;
        }
        final Allocation other = (Allocation) obj;

        return other.startaddress == this.startaddress && other.size == this.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.startaddress, this.size);
    }

    @Override
    public int compareTo(Allocation other) {
        return Float.compare(this.startaddress, other.startaddress);
    }
}
