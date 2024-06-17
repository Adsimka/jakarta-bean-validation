package com.model;

import jakarta.validation.constraints.Min;
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

    public void addUser(@Min(18) int age, @NotNull Gender gender, @NotNull String name) {
        User user = new Adult(age, gender, name);
        users.add(user);
    }

    @NotNull
    public List<@NotNull User> getUsers() {
        return users;
    }
}
