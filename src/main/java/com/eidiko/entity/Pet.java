package com.eidiko.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Pet {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String breed;
    private String age;
    private String gender;
    private String weight;
}
