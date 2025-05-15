package com.travelify.travelify.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String fullName;

    @Column(unique = true)
    String email;

    String password;
    Set<String> roles;

    boolean enabled;

    @Column(name = "email_verified")
    boolean emailVerified;
}
