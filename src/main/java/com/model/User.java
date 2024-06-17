package com.model;

import com.validation.annotation.ValidUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ValidUser(maleMinAge = 18, femaleMinAge = 20)
public class User {

    private String name;

    private int age;

    private Gender gender;


}
