package org.example.resources.http;

import org.example.resources.adapters.db.RestaurantsRepositoryImpl;
import org.example.resources.adapters.db.UsersRepositoryImpl;
import org.example.resources.ports.db.RestaurantsRepository;
import org.example.resources.ports.db.UsersRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.UUID;

@Configuration
public class ResourcesConfiguration {

    @Bean
    public UsersRepository usersRepository(DataSource dataSource) {
        return new UsersRepositoryImpl(dataSource, UUID::randomUUID);
    }

    @Bean
    public RestaurantsRepository restaurantsRepository(DataSource dataSource) {
        return new RestaurantsRepositoryImpl(dataSource, UUID::randomUUID);
    }

}
