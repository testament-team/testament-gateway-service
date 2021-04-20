package dev.testament.gateway.user.services;

import dev.testament.gateway.user.models.User;

public interface UserService {
    User findUser(String host, String email);
}
