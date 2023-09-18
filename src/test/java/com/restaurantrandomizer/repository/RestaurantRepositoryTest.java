package com.restaurantrandomizer.repository;

import com.restaurantrandomizer.model.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class RestaurantRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    @Sql("/data.sql") // Add this annotation if you want to load test data from an SQL file
    public void testFindRandomRestaurant() {
        Restaurant randomRestaurant = restaurantRepository.findRandomRestaurant();
        assertNotNull(randomRestaurant);
    }
}

