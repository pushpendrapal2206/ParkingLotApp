package com.dkatalis.parkinglot.api.models;

import java.util.Date;

/**
 * @author Pushpendra Pal
 */
public class Ticket {
    private String ticketId;
    private int entryPointId;
    private int slotNumber;
    private Date issuedAt;
    private Date paidAt;
    private int amountPaid;
    private TicketStatus status;


    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public int getEntryPointId() {
        return entryPointId;
    }

    public void setEntryPointId(int entryPointId) {
        this.entryPointId = entryPointId;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public Date getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Date paidAt) {
        this.paidAt = paidAt;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    @Override
    public int hashCode() {
        return ticketId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Ticket) {
            Ticket ticket = (Ticket) obj;
            return this.ticketId.equals(ticket.ticketId);
        }
        return false;
    }
}
