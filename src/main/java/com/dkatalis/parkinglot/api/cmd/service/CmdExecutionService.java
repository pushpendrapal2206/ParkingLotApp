package com.dkatalis.parkinglot.api.cmd.service;

import com.dkatalis.parkinglot.api.cmd.Command;

import java.util.HashMap;

import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.OPERATION_NOT_SUPPORTED;

/**
 * @author Pushpendra Pal
 */
public abstract class CmdExecutionService {
    private final HashMap<String, Command> commandMap = new HashMap<>();

    public boolean validateCommand(String commandName, int argsLength) {
        Command cmd = commandMap.get(commandName.trim());
        if (cmd == null) return false;
        return argsLength >= cmd.getExpectedArguments();
    }

    public void register(Command command) {
        commandMap.put(command.getCommandName(), command);
    }

    public void execute(String commandName, String[] args) {
        Command command = commandMap.get(commandName);
        if (command == null) {
            throw new IllegalStateException(OPERATION_NOT_SUPPORTED);
        }
        command.execute(args);
    }

    public abstract void initParkingCommands();
}
