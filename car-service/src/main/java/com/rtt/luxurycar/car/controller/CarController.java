package com.rtt.luxurycar.car.controller;

import com.rtt.luxurycar.car.model.Car;
import com.rtt.luxurycar.car.service.CarService;
import com.rtt.luxurycar.common.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ApiResponse<List<Car>> listCars(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer seats,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String search
    ) {
        return ApiResponse.ok(carService.searchCars(city, type, seats, minPrice, maxPrice, search));
    }

    @GetMapping("/{id}")
    public ApiResponse<Car> getCar(@PathVariable Long id) {
        return ApiResponse.ok(carService.getCar(id));
    }

    @PostMapping
    public ApiResponse<Car> create(@RequestBody Car car) {
        // TODO: restrict to admin
        return ApiResponse.ok(carService.createCar(car));
    }

    @PutMapping("/{id}")
    public ApiResponse<Car> update(@PathVariable Long id, @RequestBody Car car) {
        return ApiResponse.ok(carService.updateCar(id, car));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        carService.deleteCar(id);
        return ApiResponse.ok(null);
    }
}
