package com.model;

import jakarta.validation.constraints.NotNull;

import java.util.LinkedList;
import java.util.List;

public class UserGroup {

    private List<@NotNull User> users;

    public UserGroup() {
        users = new LinkedList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }
}
