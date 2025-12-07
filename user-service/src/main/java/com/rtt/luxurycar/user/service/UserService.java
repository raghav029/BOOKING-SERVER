package com.rtt.luxurycar.user.service;

import com.rtt.luxurycar.common.exception.BusinessException;
import com.rtt.luxurycar.user.dto.UserProfileDto;
import com.rtt.luxurycar.user.model.FavoriteCar;
import com.rtt.luxurycar.user.model.User;
import com.rtt.luxurycar.user.repository.FavoriteCarRepository;
import com.rtt.luxurycar.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FavoriteCarRepository favoriteCarRepository;

    public UserService(UserRepository userRepository,
                       FavoriteCarRepository favoriteCarRepository) {
        this.userRepository = userRepository;
        this.favoriteCarRepository = favoriteCarRepository;
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("USER_NOT_FOUND", "User not found"));
    }

    @Transactional
    public UserProfileDto updateProfile(Long userId, UserProfileDto dto) {
        User user = getById(userId);
        user.setName(dto.getName());
        user.setCity(dto.getCity());
        userRepository.save(user);

        UserProfileDto result = new UserProfileDto();
        result.setId(user.getId());
        result.setName(user.getName());
        result.setEmail(user.getEmail());
        result.setCity(user.getCity());
        return result;
    }

    @Transactional
    public void addFavoriteCar(Long userId, Long carId) {
        User user = getById(userId);
        favoriteCarRepository.findByUserAndCarId(user, carId).ifPresent(fc -> {
            throw new BusinessException("FAVORITE_EXISTS", "Car already in favorites");
        });
        FavoriteCar fc = new FavoriteCar();
        fc.setUser(user);
        fc.setCarId(carId);
        favoriteCarRepository.save(fc);
    }

    @Transactional
    public void removeFavoriteCar(Long userId, Long carId) {
        User user = getById(userId);
        FavoriteCar fc = favoriteCarRepository.findByUserAndCarId(user, carId)
                .orElseThrow(() -> new BusinessException("FAVORITE_NOT_FOUND", "Favorite not found"));
        favoriteCarRepository.delete(fc);
    }

    public List<FavoriteCar> listFavoriteCars(Long userId) {
        User user = getById(userId);
        return favoriteCarRepository.findByUser(user);
    }
}
