package com.rtt.luxurycar.api.booking;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingGatewayController {

    private final BookingServiceClient bookingClient;

    public BookingGatewayController(BookingServiceClient bookingClient) {
        this.bookingClient = bookingClient;
    }

    // Drivers

    @PostMapping("/drivers")
    public ResponseEntity<String> createDriver(@RequestBody Object driverBody,
                                               HttpServletRequest request) {
        return bookingClient.forward(HttpMethod.POST, "/drivers", driverBody, request);
    }

    @GetMapping("/drivers")
    public ResponseEntity<String> listDrivers(HttpServletRequest request) {
        return bookingClient.forwardGet("/drivers", request);
    }

    @GetMapping("/drivers/{id}")
    public ResponseEntity<String> getDriver(@PathVariable Long id,
                                            HttpServletRequest request) {
        return bookingClient.forwardGet("/drivers/" + id, request);
    }

    // Trips

    @PostMapping("/trips")
    public ResponseEntity<String> createTrip(@RequestBody Object tripRequest,
                                             HttpServletRequest request) {
        return bookingClient.forward(HttpMethod.POST, "/trips", tripRequest, request);
    }

    @GetMapping("/trips/{id}")
    public ResponseEntity<String> getTrip(@PathVariable Long id,
                                          HttpServletRequest request) {
        return bookingClient.forwardGet("/trips/" + id, request);
    }

    @GetMapping("/trips")
    public ResponseEntity<String> listTripsByUser(@RequestParam Long userId,
                                                  HttpServletRequest request) {
        return bookingClient.forwardGet("/trips?userId=" + userId, request);
    }

    @PostMapping("/trips/{id}/assign")
    public ResponseEntity<String> assignDriverVehicle(@PathVariable Long id,
                                                      @RequestBody Object assignRequest,
                                                      HttpServletRequest request) {
        return bookingClient.forward(HttpMethod.POST, "/trips/" + id + "/assign", assignRequest, request);
    }

    @PostMapping("/trips/{id}/status")
    public ResponseEntity<String> updateTripStatus(@PathVariable Long id,
                                                   @RequestBody Object statusRequest,
                                                   HttpServletRequest request) {
        return bookingClient.forward(HttpMethod.POST, "/trips/" + id + "/status", statusRequest, request);
    }
}
