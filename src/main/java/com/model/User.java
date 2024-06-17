package com.model;

import com.validation.annotation.ValidUser;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@ValidUser(maleMinAge = 18, femaleMinAge = 20)
public abstract class User {

    @NotNull
    private String name;

    @NotNull
    private Gender gender;

    public abstract int getAge();

}
