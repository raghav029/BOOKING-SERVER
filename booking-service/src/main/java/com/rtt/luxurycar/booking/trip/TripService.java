package com.rtt.luxurycar.booking.trip;

import com.rtt.luxurycar.booking.trip.dto.AssignDriverVehicleRequest;
import com.rtt.luxurycar.booking.trip.dto.CreateTripRequest;
import com.rtt.luxurycar.booking.trip.dto.UpdateTripStatusRequest;
import com.rtt.luxurycar.common.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip create(CreateTripRequest req) {
        if (req.getUserId() == null) {
            throw new BusinessException("USER_ID_REQUIRED", "User id is required");
        }
        if (req.getPickupTime() == null) {
            throw new BusinessException("PICKUP_TIME_REQUIRED", "Pickup time is required");
        }
        if (req.getPickupLocation() == null || req.getPickupLocation().isBlank()) {
            throw new BusinessException("PICKUP_LOCATION_REQUIRED", "Pickup location is required");
        }
        if (req.getDropLocation() == null || req.getDropLocation().isBlank()) {
            throw new BusinessException("DROP_LOCATION_REQUIRED", "Drop location is required");
        }

        Trip trip = new Trip();
        trip.setUserId(req.getUserId());
        trip.setPickupTime(req.getPickupTime());
        trip.setPickupLocation(req.getPickupLocation());
        trip.setDropLocation(req.getDropLocation());
        trip.setStatus(TripStatus.SCHEDULED);

        return tripRepository.save(trip);
    }

    public Trip get(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new BusinessException("TRIP_NOT_FOUND", "Trip not found"));
    }

    public List<Trip> listByUser(Long userId) {
        return tripRepository.findByUserId(userId);
    }

    public Trip assignDriverVehicle(Long tripId, AssignDriverVehicleRequest req) {
        Trip trip = get(tripId);
        trip.setDriverId(req.getDriverId());
        trip.setCarId(req.getCarId());
        return tripRepository.save(trip);
    }

    public Trip updateStatus(Long tripId, UpdateTripStatusRequest req) {
        Trip trip = get(tripId);
        if (req.getStatus() == null) {
            throw new BusinessException("STATUS_REQUIRED", "Status is required");
        }
        trip.setStatus(req.getStatus());
        return tripRepository.save(trip);
    }
}
