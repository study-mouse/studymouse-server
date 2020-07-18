package com.studymouse.studymouseserver.user;

import com.studymouse.studymouseserver.word.Word;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Column
    private boolean isPushMail = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @OneToMany(mappedBy = "user")
    private List<Word> words = Collections.emptyList();

    @Builder
    private User(String name, String email, String picture, Role role, Type type) {
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

    public boolean togglePushMail() {
        return isPushMail ? disablePushMail() : enablePushMail();
    }

    private boolean enablePushMail() {
        return this.isPushMail = true;
    }

    private boolean disablePushMail() {
        return this.isPushMail = false;
    }
}
