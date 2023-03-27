package org.example.resources.http.controller;

import org.example.resources.domain.entity.Restaurant;
import org.example.resources.domain.entity.type.RestaurantId;
import org.example.resources.infrastructure.db.dao.RestaurantsDao;
import org.example.resources.infrastructure.db.table.restaurants.tables.records.RestaurantRecord;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {

    private final RestaurantsDao restaurantsDao;

    public RestaurantController(RestaurantsDao restaurantsDao) {
        this.restaurantsDao = restaurantsDao;
    }

    @PostMapping("/generate")
    public Restaurant generate() {
        RestaurantRecord record = restaurantsDao.generateRestaurant();
        return new Restaurant(new RestaurantId(record.getId()), record.getName());
    }

    @GetMapping("/")
    public List<Restaurant> getAll() {
        return restaurantsDao.getAll().stream()
                .map(record -> new Restaurant(new RestaurantId(record.getId()), record.getName()))
                .toList();
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable UUID id) {
        RestaurantRecord record = restaurantsDao.get(id);
        return new Restaurant(new RestaurantId(record.getId()), record.getName());
    }

}
