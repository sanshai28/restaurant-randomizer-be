package com.restaurantrandomizer.repository;

import com.restaurantrandomizer.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
 public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query(value = "SELECT * FROM restaurants ORDER BY random() LIMIT 1", nativeQuery = true)
    Restaurant findRandomRestaurant();

}
