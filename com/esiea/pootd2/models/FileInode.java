package com.esiea.pootd2.models;

public class FileInode extends Inode {
    private final static int MIN_SIZE = 1;
    private final static int MAX_SIZE = 100_000;

    private int size;

    public FileInode(String name) {
        super(name);

        this.size = (int) (Math.random() * MAX_SIZE) + MIN_SIZE;
    }

    @Override
    public int getSize() {
        return this.size;
    }
}
