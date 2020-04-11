package com.dkatalis.parkinglot.api.cmd.service;

import com.dkatalis.parkinglot.api.ParkingLot;
import com.dkatalis.parkinglot.api.cmd.Command;
import com.dkatalis.parkinglot.api.common.ParkingLotConstant;

/**
 * @author Pushpendra Pal
 */
public class ParkingCmdExecutionService extends CmdExecutionService {
    private final ParkingLot parkingLot = ParkingLot.getInstance();

    public ParkingCmdExecutionService() {
        initParkingCommands();
    }

    @Override
    public void initParkingCommands() {
        initCommands();
    }

    private void initCommands() {
        register(new Command(ParkingLotConstant.CREATE_PARKING_LOT, 1, parkingLot::createParkingLot));
        register(new Command(ParkingLotConstant.PARK, 1, parkingLot::park));
        register(new Command(ParkingLotConstant.LEAVE, 2, parkingLot::leave));
        register(new Command(ParkingLotConstant.STATUS, 0, parkingLot::getParkingStatus));
    }
}
