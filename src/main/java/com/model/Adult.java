package com.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Adult extends User {

    private int age;

    public Adult(@Min(18) int age,
                 @NotNull Gender gender,
                 @NotNull String name) {
        super(name, gender);
        this.age = age;
    }
}
