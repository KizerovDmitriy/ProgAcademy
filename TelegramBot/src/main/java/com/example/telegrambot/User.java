package com.example.telegrambot;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "bot_users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long chatId;

    @Column(nullable = false)
    private Long state;

    @Column(nullable = false)
    private Boolean admin;

    private String name;
    private String phone;

    // todo

    public void incrementState() {
        state++;
    }

}
