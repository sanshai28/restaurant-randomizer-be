package com.restaurantrandomizer.service;

import com.restaurantrandomizer.dto.RestaurantDTO;
import com.restaurantrandomizer.model.Restaurant;
import com.restaurantrandomizer.repository.RestaurantRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import com.restaurantrandomizer.exceptions.RestaurantNotFoundException;
import static org.mockito.Mockito.*;

public class RestaurantServiceTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetRandomRestaurant() {

        Restaurant restaurant = new Restaurant();
        restaurant.setName("Sample Restaurant");


        when(restaurantRepository.findRandomRestaurant()).thenReturn(restaurant);


        RestaurantDTO restaurantDTO = restaurantService.getRandomRestaurant();


        verify(restaurantRepository, times(1)).findRandomRestaurant();


        assertNotNull(restaurantDTO);
        assertEquals("Sample Restaurant", restaurantDTO.getName());
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void testGetRandomRestaurantWhenNoRestaurantFound() {
        // Mock the behavior of the repository when no restaurant is found
        when(restaurantRepository.findRandomRestaurant()).thenReturn(null);

        // Call the service method, which should throw RestaurantNotFoundException
        restaurantService.getRandomRestaurant();
    }
}

