package com.rtt.luxurycar.booking.trip.dto;

import com.rtt.luxurycar.booking.trip.TripStatus;

public class UpdateTripStatusRequest {

    private TripStatus status;

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }
}
