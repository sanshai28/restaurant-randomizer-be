package com.restaurantrandomizer.service;

import com.restaurantrandomizer.dto.RestaurantDTO;
import com.restaurantrandomizer.exceptions.RestaurantDataValidationException;
import com.restaurantrandomizer.exceptions.RestaurantNotFoundException;
import com.restaurantrandomizer.model.Restaurant;
import com.restaurantrandomizer.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public RestaurantDTO    getRandomRestaurant() {
        Optional<Restaurant> randomRestaurant = Optional.ofNullable(restaurantRepository.findRandomRestaurant());

        if (randomRestaurant.isPresent()) {
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setName(randomRestaurant.get().getName());
            return restaurantDTO;
        } else {
            throw new RestaurantNotFoundException("No random restaurant found.");
        }
    }

    @Transactional
    public void addRestaurant(RestaurantDTO restaurantDTO) {
        // Perform any validation checks on restaurantDTO if needed
        if (restaurantDTO.getName() == null || restaurantDTO.getName().isEmpty()) {
            throw new RestaurantDataValidationException("Restaurant name is required.");
        }

        // Create a Restaurant entity from the DTO
        Restaurant  restaurant = new Restaurant();
        restaurant.setName(restaurantDTO.getName());

        // Save the restaurant to the repository
        restaurantRepository.save(restaurant);
    }

}
