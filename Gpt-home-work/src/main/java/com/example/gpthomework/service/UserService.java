package com.example.gpthomework.service;

import com.example.gpthomework.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final String EXISTING_EMAIL = "test@test.com";
    private static final String ANOTHER_EMAIL = "next@test.com";

    public Optional<UserEntity> findByEmail(String email) {

        //TODO: move this to DataBase

        if (EXISTING_EMAIL.equalsIgnoreCase(email)) {

            var user = new UserEntity();
            user.setId(1L);
            user.setEmail(EXISTING_EMAIL);
            user.setPassword("$2a$12$Ry2agyo7a187GXLxIApa/.GDjk6o1Wtm8KoXjJhMQwj7swpFfrNMa"); //test
            user.setRole("ROLE_ADMIN");
            user.setExtraInfo("My nice admin");

            return Optional.of(user);

        } else if (ANOTHER_EMAIL.equalsIgnoreCase(email)) {
            var user = new UserEntity();
            user.setId(99L);
            user.setEmail(ANOTHER_EMAIL);
            user.setPassword("$2a$12$Ry2agyo7a187GXLxIApa/.GDjk6o1Wtm8KoXjJhMQwj7swpFfrNMa"); //test
            user.setRole("ROLE_USER");
            user.setExtraInfo("My nice user");

            return Optional.of(user);
        }

        return Optional.empty();

    }

}