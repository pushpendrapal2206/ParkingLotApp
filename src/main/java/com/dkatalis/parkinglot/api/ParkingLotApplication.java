package com.dkatalis.parkinglot.api;

import com.dkatalis.parkinglot.api.cmd.CmdExecutor;
import com.dkatalis.parkinglot.api.cmd.service.CmdExecutionService;
import com.dkatalis.parkinglot.api.cmd.service.ParkingCmdExecutionService;
import com.dkatalis.parkinglot.api.common.ParkingLotConstant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * @author Pushpendra Pal
 */
public class ParkingLotApplication {

    public static void main(String[] args) {
        CmdExecutor processor;
        CmdExecutionService service = new ParkingCmdExecutionService();
        try {
            switch (args.length) {
                case 0: {
                    processor = new CmdExecutor(service);
                    try (BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in))) {
                        processor.startCmdProcessing(stdin);
                    }
                    break;
                }
                case 1: {
                    processor = new CmdExecutor(service);
                    try (BufferedReader stdin = new BufferedReader(new FileReader(args[0]))) {
                        processor.startCmdProcessing(stdin);
                    }
                    break;
                }
                default:
                    System.out.println(ParkingLotConstant.INVALID_COMMAND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
