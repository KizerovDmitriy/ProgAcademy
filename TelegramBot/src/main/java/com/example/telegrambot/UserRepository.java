package com.example.telegrambot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByChatId(long chatId);
    void deleteUserByChatId(long chatId);
    void deleteUserById(long id);
    Optional<User> findUserById (long id);
}
