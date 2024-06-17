package com.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Adult extends User {

    private int age;

    public Adult(int age, Gender gender, String name) {
        super(name, gender);
        this.age = age;
    }
}
