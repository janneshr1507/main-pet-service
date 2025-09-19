package com.eidiko.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Grooming {
    @Id
    @GeneratedValue
    private Long id;
    private String petName;
    private List<String> services;
    private String date;
    private String time;
    private String status;
}
