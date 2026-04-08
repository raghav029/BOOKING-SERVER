package com.rtt.luxurycar.booking.driver;

import com.rtt.luxurycar.common.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public ApiResponse<Driver> create(@RequestBody Driver driver) {
        return ApiResponse.ok(driverService.create(driver));
    }

    @GetMapping
    public ApiResponse<List<Driver>> list() {
        return ApiResponse.ok(driverService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse<Driver> get(@PathVariable Long id) {
        return ApiResponse.ok(driverService.get(id));
    }
}
