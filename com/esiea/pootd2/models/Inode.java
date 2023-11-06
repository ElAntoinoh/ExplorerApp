package com.esiea.pootd2.models;

public abstract class Inode {
    private String name;
    protected FolderInode parent;

    public Inode(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public FolderInode getParent() {
        return this.parent;
    }

    public abstract int getSize();
}
