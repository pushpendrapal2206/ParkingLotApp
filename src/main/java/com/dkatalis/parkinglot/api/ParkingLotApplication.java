package com.dkatalis.parkinglot.api;

import com.dkatalis.parkinglot.api.cmd.CmdExecutor;
import com.dkatalis.parkinglot.api.cmd.service.CmdExecutionService;
import com.dkatalis.parkinglot.api.common.ParkingLotConstant;
import com.dkatalis.parkinglot.api.cmd.service.ParkingCmdExecutionService;

import java.io.File;
import java.util.Scanner;

/**
 * @author Pushpendra Pal
 */
public class ParkingLotApplication {

    public static void main(String[] args) {
        CmdExecutor processor = null;
        CmdExecutionService service = new ParkingCmdExecutionService();
        try {

            switch (args.length) {
                case 0: {
                    processor = new CmdExecutor(new Scanner(System.in));
                    processor.setExecutionService(service);
                    processor.startCmdProcessing();
                    break;
                }
                case 1: {
                    File inputFile = new File(args[0]);
                    processor = new CmdExecutor(new Scanner(inputFile));
                    processor.setExecutionService(service);
                    processor.startCmdProcessing();
                    break;
                }
                default:
                    System.out.println(ParkingLotConstant.INVALID_COMMAND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            if (processor != null) {
                processor.cleanUp();
            }
        }
    }
}
