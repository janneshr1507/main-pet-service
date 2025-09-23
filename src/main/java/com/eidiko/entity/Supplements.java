package com.eidiko.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Supplements {
    @Id
    @GeneratedValue
    private Long id;
    private Long petId;
    private Long ownerId;
    private String petName;
    private List<String> selectedSupplements;
    private String quantity;
    private String date;
}
