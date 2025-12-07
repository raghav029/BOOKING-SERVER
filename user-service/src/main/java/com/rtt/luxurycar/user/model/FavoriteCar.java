package com.rtt.luxurycar.user.model;

import com.rtt.luxurycar.common.model.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "favorite_cars",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "car_id"}))
public class FavoriteCar extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "car_id", nullable = false)
    private Long carId;

    public FavoriteCar() {}

    public FavoriteCar(User user, Long carId) {
        this.user = user;
        this.carId = carId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}
