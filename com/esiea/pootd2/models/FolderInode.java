package com.esiea.pootd2.models;

import java.util.ArrayList;

public class FolderInode extends Inode {
    ArrayList<Inode> children = new ArrayList<>();

    public FolderInode(String name) {
        super(name);
    }

    public void addInode(Inode inode) {
        this.children.add(inode);
        inode.parent = this;
    }

    @Override
    public int getSize() {
        int size = 0;

        for (Inode child : this.children) {
            size += child.getSize();
        }

        return size;
    }

    public FolderInode findChildFolder(String name) {
        for (Inode inode : this.children) {
            if (inode instanceof FolderInode) {
                if (inode.getName().equals(name)) {
                    return (FolderInode) inode;
                }
            }
        }

        return null;
    }

    public ArrayList<Inode> getChildren() {
        return this.children;
    }
}
