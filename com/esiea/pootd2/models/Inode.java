package com.esiea.pootd2.models;

public abstract class Inode {
    private String name;
    protected Inode parent;

    public Inode(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Inode getParent() {
        return this.parent;
    }

    public abstract int getSize();
}
