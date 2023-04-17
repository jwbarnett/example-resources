package org.example.resources.http.controller;

import org.example.resources.domain.entity.Restaurant;
import org.example.resources.domain.entity.type.RestaurantId;
import org.example.resources.ports.db.RestaurantsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {

    private final RestaurantsRepository restaurantsRepository;

    public RestaurantController(RestaurantsRepository restaurantsRepository) {
        this.restaurantsRepository = restaurantsRepository;
    }

    @PostMapping("/generate")
    public Restaurant generate() {
        return restaurantsRepository.generateRestaurant();
    }

    @GetMapping("/")
    public List<Restaurant> getAll() {
        return restaurantsRepository.getAll().stream()
                .toList();
    }

    @GetMapping("/{id}")
    public Optional<Restaurant> get(@PathVariable UUID id) {
        return restaurantsRepository.get(new RestaurantId(id));
    }

}
