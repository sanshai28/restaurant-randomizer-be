package com.restaurantrandomizer.controller;

import com.restaurantrandomizer.dto.RestaurantDTO;
import com.restaurantrandomizer.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/random")
    public ResponseEntity<RestaurantDTO> getRandomRestaurant() {
        RestaurantDTO randomRestaurantDTO = restaurantService.getRandomRestaurant();
        return ResponseEntity.ok(randomRestaurantDTO);
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        restaurantService.addRestaurant(restaurantDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantDTO);
    }
}
