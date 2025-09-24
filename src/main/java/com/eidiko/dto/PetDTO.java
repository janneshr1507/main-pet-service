package com.eidiko.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class PetDTO {
    private Long id;
    private Long ownerId;
    private String name;
    private String breed;
    private String age;
    private String gender;
    private String weight;
}
