package com.rtt.luxurycar.booking.trip;

import com.rtt.luxurycar.booking.trip.dto.AssignDriverVehicleRequest;
import com.rtt.luxurycar.booking.trip.dto.CreateTripRequest;
import com.rtt.luxurycar.booking.trip.dto.UpdateTripStatusRequest;
import com.rtt.luxurycar.common.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public ApiResponse<Trip> create(@RequestBody CreateTripRequest request) {
        return ApiResponse.ok(tripService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<Trip> get(@PathVariable Long id) {
        return ApiResponse.ok(tripService.get(id));
    }

    @GetMapping
    public ApiResponse<List<Trip>> listByUser(@RequestParam Long userId) {
        return ApiResponse.ok(tripService.listByUser(userId));
    }

    @PostMapping("/{id}/assign")
    public ApiResponse<Trip> assignDriverVehicle(@PathVariable Long id,
                                                 @RequestBody AssignDriverVehicleRequest request) {
        return ApiResponse.ok(tripService.assignDriverVehicle(id, request));
    }

    @PostMapping("/{id}/status")
    public ApiResponse<Trip> updateStatus(@PathVariable Long id,
                                          @RequestBody UpdateTripStatusRequest request) {
        return ApiResponse.ok(tripService.updateStatus(id, request));
    }
}
