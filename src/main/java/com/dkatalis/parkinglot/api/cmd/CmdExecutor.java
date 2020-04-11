package com.dkatalis.parkinglot.api.cmd;

import com.dkatalis.parkinglot.api.cmd.service.CmdExecutionService;

import java.util.Arrays;
import java.util.Scanner;

import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.INVALID_COMMAND;

/**
 * @author Pushpendra Pal
 */
public class CmdExecutor {
    private Scanner inputReader;
    private CmdExecutionService service;

    public CmdExecutor(Scanner inputReader) {
        this.inputReader = inputReader;
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

    public void startCmdProcessing() throws Exception {
        String input;
        while (true) {
            try {
                while (inputReader.hasNext()) {
                    input = inputReader.nextLine().trim();
                    if (input.equalsIgnoreCase("stop")) {
                        cleanUp();
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
                }
            } catch (Exception e) {
                throw new Exception("Error", e);
            }
        }
    }

    public void setExecutionService(CmdExecutionService service) {
        this.service = service;
    }

    private boolean validateCommand(String input, int passedArgs) {
        return service.validateCommand(input, passedArgs);
    }

    private void execute(String command, String[] args) {
        service.execute(command, args);
    }

    public void cleanUp() {
        if (inputReader != null)
            inputReader.close();
    }
}
