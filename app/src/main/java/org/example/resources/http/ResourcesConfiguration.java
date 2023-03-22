package org.example.resources.http;

import org.example.resources.infrastructure.db.dao.RestaurantsDao;
import org.example.resources.infrastructure.db.dao.UsersDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ResourcesConfiguration {

    @Bean
    public UsersDao usersDao(DataSource dataSource) {
        return new UsersDao(dataSource);
    }

    @Bean
    public RestaurantsDao restaurantsDao(DataSource dataSource) {
        return new RestaurantsDao(dataSource);
    }

}
