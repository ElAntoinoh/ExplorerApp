package com.esiea.pootd2.models;

import java.util.ArrayList;

public class FolderInode extends Inode {
    ArrayList<Inode> children;

    public FolderInode(String name) {
        super(name);

        this.children = new ArrayList<>();
    }

    public void addInode(Inode inode) {
        this.children.add(inode);
    }

    @Override
    public int getSize() {
        int size = 0;

        for (Inode child : this.children) {
            size += child.getSize();
        }

        return size;
    }
}
