package com.test.spring.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Table(name = "user_entity")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long userId;
    @Column(nullable = false, unique = true, length = 45)
    @NonNull
    private String email;
    @Column(nullable = false, length = 45)
    @NonNull
    private String password;
    @Column(name = "first_name", nullable = false, length = 45)
    @NonNull
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 45)
    @NonNull
    private String lastName;
}
