package com.esiea.pootd2.commands;

public abstract class Command {
    private String[] args;

    public Command(String[] args) {
        this.args = args;
    }

    public String[] getArgs() {
        return this.args;
    }
}
