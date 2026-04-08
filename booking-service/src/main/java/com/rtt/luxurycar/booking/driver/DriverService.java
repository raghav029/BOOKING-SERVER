package com.rtt.luxurycar.booking.driver;

import com.rtt.luxurycar.common.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver create(Driver input) {
        driverRepository.findByPhone(input.getPhone()).ifPresent(d -> {
            throw new BusinessException("DRIVER_PHONE_EXISTS", "Driver with this phone already exists");
        });
        return driverRepository.save(input);
    }

    public Driver get(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new BusinessException("DRIVER_NOT_FOUND", "Driver not found"));
    }

    public List<Driver> list() {
        return driverRepository.findAll();
    }
}
