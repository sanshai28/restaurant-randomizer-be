package com.restaurantrandomizer.controller;

import com.restaurantrandomizer.dto.RestaurantDTO;
import com.restaurantrandomizer.service.RestaurantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    @Test
    public void testGetRandomRestaurant() throws Exception {
        RestaurantDTO mockRestaurant = new RestaurantDTO();
        mockRestaurant.setName("Example Restaurant"); // Set the name field


        when(restaurantService.getRandomRestaurant()).thenReturn(mockRestaurant);

        mockMvc.perform(get("/api/restaurants/random"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Example Restaurant"));


        verify(restaurantService, times(1)).getRandomRestaurant();
    }

    @Test
    public void testAddRestaurant() throws Exception {
        RestaurantDTO restaurantDTO = new RestaurantDTO();

        doNothing().when(restaurantService).addRestaurant(restaurantDTO);

        mockMvc.perform(post("/api/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(restaurantDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Restaurant added successfully."));

        // Verify that the service method was called
        verify(restaurantService, times(1)).addRestaurant(restaurantDTO);
    }

    // Utility method to convert an object to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
