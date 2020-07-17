package com.studymouse.studymouseserver.security.dto;

import com.studymouse.studymouseserver.user.Role;
import com.studymouse.studymouseserver.user.Type;
import com.studymouse.studymouseserver.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;
    private Type type;

    @Builder
    public OAuthAttributes(final Map<String, Object> attributes, final String nameAttributeKey, final String name, final String email, final String picture, final Type type) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.type = type;
    }


    public static OAuthAttributes of(final String registrationId, final String userNameAttributeName, final Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(final String userNameAttributeName, final Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .type(Type.SOCIAL)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }


    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.USER)
                .type(type)
                .build();
    }
}
