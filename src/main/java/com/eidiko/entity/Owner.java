package com.eidiko.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString
public class Owner {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String contact;
    private String password;
    private String role;
}
