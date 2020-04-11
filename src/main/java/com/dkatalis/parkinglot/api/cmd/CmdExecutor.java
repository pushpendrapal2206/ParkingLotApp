package com.dkatalis.parkinglot.api.cmd;

import com.dkatalis.parkinglot.api.cmd.service.CmdExecutionService;

import java.io.BufferedReader;
import java.util.Arrays;

import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.INVALID_COMMAND;

/**
 * @author Pushpendra Pal
 */
public class CmdExecutor {
    private CmdExecutionService service;

    public CmdExecutor(final CmdExecutionService service) {
        this.service = service;
    }

    public void startCmdProcessing(final BufferedReader inputReader) throws Exception {
        String input;
        while (((input = inputReader.readLine()) != null && input.length() != 0)) {
            try {
                if (input.equalsIgnoreCase("stop")) {
                    break;
                } else {
                    String[] commandWithArgs = getCmdAndArgs(input);
                    String command = commandWithArgs[0];
                    String[] args = getArgs(commandWithArgs);
                    if (this.validateCommand(command, args.length)) {
                        try {
                            this.execute(command, args);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(INVALID_COMMAND);
                    }
                }
            } catch (Exception e) {
                throw new Exception("Error", e);
            }
        }
    }

    private boolean validateCommand(String input, int passedArgs) {
        return service.validateCommand(input, passedArgs);
    }

    private String[] getCmdAndArgs(String input) {
        return input.split(" ");
    }

    private String[] getArgs(String[] cmdWithArgs) {
        String[] args = new String[]{};
        if (cmdWithArgs.length > 1) {
            args = Arrays.copyOfRange(cmdWithArgs, 1, cmdWithArgs.length);
        }
        return args;
    }

    private void execute(String command, String[] args) {
        service.execute(command, args);
    }
}
