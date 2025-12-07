package com.rtt.luxurycar.car.service;

import com.rtt.luxurycar.car.model.Car;
import com.rtt.luxurycar.car.repository.CarRepository;
import com.rtt.luxurycar.common.exception.BusinessException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> searchCars(String city, String type, Integer seats,
                                Double minPrice, Double maxPrice, String search) {

        Specification<Car> spec = (root, query, cb) -> {
            List<Predicate> preds = new ArrayList<>();

            if (city != null && !city.isBlank()) {
                preds.add(cb.equal(root.get("city"), city));
            }
            if (type != null && !type.isBlank()) {
                preds.add(cb.equal(root.get("type"), type));
            }
            if (seats != null) {
                preds.add(cb.greaterThanOrEqualTo(root.get("seats"), seats));
            }
            if (minPrice != null) {
                preds.add(cb.greaterThanOrEqualTo(root.get("hourlyPrice"), minPrice));
            }
            if (maxPrice != null) {
                preds.add(cb.lessThanOrEqualTo(root.get("hourlyPrice"), maxPrice));
            }
            if (search != null && !search.isBlank()) {
                String like = "%" + search.toLowerCase() + "%";
                preds.add(cb.or(
                        cb.like(cb.lower(root.get("name")), like),
                        cb.like(cb.lower(root.get("type")), like)
                ));
            }

            return cb.and(preds.toArray(new Predicate[0]));
        };

        return carRepository.findAll(spec);
    }

    public Car getCar(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new BusinessException("CAR_NOT_FOUND", "Car not found"));
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car input) {
        Car car = getCar(id);
        car.setName(input.getName());
        car.setCity(input.getCity());
        car.setType(input.getType());
        car.setSeats(input.getSeats());
        car.setFuel(input.getFuel());
        car.setHourlyPrice(input.getHourlyPrice());
        car.setDailyPrice(input.getDailyPrice());
        car.setImageUrl(input.getImageUrl());
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
