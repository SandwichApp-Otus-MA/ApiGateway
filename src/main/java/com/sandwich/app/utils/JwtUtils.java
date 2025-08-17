package com.sandwich.app.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class JwtUtils {

    public boolean isSameUser(Authentication authentication, UUID targetUserId) {
        var jwt = (Jwt) authentication.getPrincipal();
        var userIdFromToken = jwt.getClaim("userId");
        return Optional.ofNullable(userIdFromToken)
            .map(Object::toString)
            .map(UUID::fromString)
            .stream()
            .anyMatch(userId -> Objects.equals(targetUserId, userId));
    }
}
