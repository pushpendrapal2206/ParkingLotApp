package com.dkatalis.parkinglot.api.cmd;

/**
 * @author Pushpendra Pal
 */
public class Command {
    private CmdBinder commandTo;
    private int expectedArguments;
    private String commandName;

    public String getCommandName() {
        return commandName;
    }

    public int getExpectedArguments() {
        return expectedArguments;
    }

    public Command(String commandName, int requiredArgs, CmdBinder cmd) {
        this.commandName = commandName;
        this.expectedArguments = requiredArgs;
        this.commandTo = cmd;
    }

    public void execute(String[] args) {
        if (args.length < expectedArguments) {
            throw new IllegalArgumentException(commandName + " requires " + expectedArguments + " arguments!");
        }
        commandTo.execute(args);
    }
}