package com.dkatalis.parkinglot.api;

import com.dkatalis.parkinglot.api.common.ParkingLotConstant;
import com.dkatalis.parkinglot.api.models.Car;
import com.dkatalis.parkinglot.api.models.ParkingRate;
import com.dkatalis.parkinglot.api.models.ParkingSlot;
import com.dkatalis.parkinglot.api.models.Ticket;
import com.dkatalis.parkinglot.api.models.TicketStatus;
import com.dkatalis.parkinglot.api.models.Vehicle;
import com.dkatalis.parkinglot.api.strategy.BillingStrategy;
import com.dkatalis.parkinglot.api.strategy.DefaultBillingStrategy;

import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Pushpendra Pal
 */
public class ParkingLot {
    private static ParkingLot parkingLot;
    private static final int defaultEntryPointId = 1;
    private BillingStrategy billingStrategy;
    private Map<String, Ticket> parkingTicketMap;
    private Map<Integer, ParkingSlot> parkingSlotMap;


    private ParkingLot() {
        //concurrent hash maps are being used to handle concurrent requests.
        this.parkingTicketMap = new ConcurrentHashMap<>();
        this.parkingSlotMap = new ConcurrentHashMap<>();
        final ParkingRate rate = new ParkingRate(10);
        this.billingStrategy = new DefaultBillingStrategy(rate);
    }

    public static ParkingLot getInstance() {
        if (parkingLot == null) {
            parkingLot = new ParkingLot();
        }
        return parkingLot;
    }

    public void createParkingLot(final String[] args) {
        int capacity = Integer.parseInt(args[0]);
        for (int i = 1; i <= capacity; i++) {
            ParkingSlot parkingSlot = new ParkingSlot(i);
            this.parkingSlotMap.put(parkingSlot.getSlotNumber(), parkingSlot);
        }
        System.out.println(String.format(ParkingLotConstant.PARKING_LOT_CREATED_MESSAGE, capacity));
    }

    public void park(final String[] args) {
        final String registrationNum = args[0];
        final Vehicle vehicle = new Car();
        vehicle.setRegistrationNumber(registrationNum);
        Optional<ParkingSlot> optionalParkingSlot = parkingSlotMap.values().stream()
                .filter(ParkingSlot::isFree)
                .findFirst();
        if (optionalParkingSlot.isPresent()) {
            ParkingSlot parkingSlot = optionalParkingSlot.get();
            parkingSlot.assignVehicle(vehicle);
            getNewParkingTicket(parkingSlot, vehicle, defaultEntryPointId);
            System.out.println(String.format(ParkingLotConstant.PARKING_SLOT_ALLOCATED_MESSAGE, parkingSlot.getSlotNumber()));
            return;
        }
        System.out.println(ParkingLotConstant.PARKING_LOT_FULL_MESSAGE);
    }

    public void leave(final String[] args) {
        final String registrationNumber = args[0];
        final int hours = Integer.parseInt(args[1]);
        Optional<ParkingSlot> optionalParkingSlot = parkingSlotMap.values()
                .stream()
                .filter(parkingSlot -> parkingSlot.getVehicle() != null
                        && parkingSlot.getVehicle().getRegistrationNumber().equals(registrationNumber))
                .findFirst();
        if (optionalParkingSlot.isPresent()) {
            ParkingSlot parkingSlot = optionalParkingSlot.get();
            int charges = billingStrategy.calculateCharges(hours);
            updateAssignedTicket(parkingSlot, charges);
            parkingSlot.removeVehicle();
            System.out.println(String.format(ParkingLotConstant.CAR_UNPARK_MESSAGE,
                    registrationNumber,
                    parkingSlot.getSlotNumber(),
                    charges));
        } else {
            System.out.println(String.format(ParkingLotConstant.CAR_ALREADY_UNPARKED_MESSAGE, registrationNumber));
        }
    }

    public void getParkingStatus(String[] args) {
        System.out.println(String.format("%-12s%-19s", "Slot No.",
                "Registration No"));
        this.parkingSlotMap.values()
                .stream()
                .filter(value -> value.getVehicle() != null)
                .forEach(value -> {
                    Vehicle vehicle = value.getVehicle();
                    System.out.println(
                            String.format("%-12s%-19s",
                                    vehicle.getTicket().getSlotNumber(),
                                    vehicle.getRegistrationNumber()
                            )
                    );
                });
    }

    private void getNewParkingTicket(final ParkingSlot parkingSlot,
                                     final Vehicle vehicle,
                                     int entryPointId) {
        Ticket ticket = new Ticket();
        ticket.setEntryPointId(entryPointId);
        ticket.setSlotNumber(parkingSlot.getSlotNumber());
        ticket.setIssuedAt(Date.from(Instant.now()));
        ticket.setTicketId(UUID.randomUUID().toString());
        ticket.setStatus(TicketStatus.ACTIVE);
        vehicle.assignTicket(ticket);
        this.parkingTicketMap.put(ticket.getTicketId(), ticket);
    }

    private void updateAssignedTicket(final ParkingSlot parkingSlot,
                                      final int charges) {
        Ticket assignedTicket = parkingSlot.getVehicle().getTicket();
        assignedTicket.setAmountPaid(charges);
        assignedTicket.setStatus(TicketStatus.PAID);
        assignedTicket.setPaidAt(Date.from(Instant.now()));
        parkingTicketMap.put(assignedTicket.getTicketId(), assignedTicket);
    }
}
