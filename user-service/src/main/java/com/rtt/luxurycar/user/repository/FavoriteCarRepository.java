package com.rtt.luxurycar.user.repository;

import com.rtt.luxurycar.user.model.FavoriteCar;
import com.rtt.luxurycar.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteCarRepository extends JpaRepository<FavoriteCar, Long> {
    List<FavoriteCar> findByUser(User user);
    Optional<FavoriteCar> findByUserAndCarId(User user, Long carId);
}
