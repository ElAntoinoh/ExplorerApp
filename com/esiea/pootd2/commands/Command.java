package com.esiea.pootd2.commands;

public abstract class Command {
    private String[] args;

    public Command(String[] args) {
        this.args = args;
    }
}
