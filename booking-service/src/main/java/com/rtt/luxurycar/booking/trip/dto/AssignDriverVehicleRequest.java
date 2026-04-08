package com.rtt.luxurycar.booking.trip.dto;

public class AssignDriverVehicleRequest {

    private Long driverId;
    private Long carId;

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}
