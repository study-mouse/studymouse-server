package com.studymouse.studymouseserver.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Builder
    public User(String name, String email, String picture, Role role, Type type) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.type = type;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public Type getType() {
        return type;
    }
}
