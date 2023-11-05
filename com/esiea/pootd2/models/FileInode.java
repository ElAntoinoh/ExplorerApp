package com.esiea.pootd2.models;

public class FileInode extends Inode {
    public FileInode(String name) {
        super(name);
    }

    @Override
    public int getSize() {
        return 1;
    }
}
