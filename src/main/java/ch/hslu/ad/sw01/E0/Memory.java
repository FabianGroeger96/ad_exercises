package ch.hslu.ad.sw01.E0;

public abstract class Memory {

    public abstract Allocation malloc(int size);

    public abstract void free(Allocation allocation);

}
