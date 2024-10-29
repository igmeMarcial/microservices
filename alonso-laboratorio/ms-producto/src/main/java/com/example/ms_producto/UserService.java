package com.example.ms_producto;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserClient userClient;

    public UserService(UserClient userClient) {
        this.userClient = userClient;
    }

    public User getUser(Long id) {
        return userClient.getUserById(id);
    }

}
