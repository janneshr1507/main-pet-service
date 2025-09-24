package com.eidiko.dto;

import com.eidiko.entity.Owner;
import com.eidiko.entity.Pet;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class GroomingDTO {
    @Id
    @GeneratedValue
    private Long id;
    private Long petId;
    private Long ownerId;
    private String petName;
    private List<String> services;
    private String date;
    private String time;
    private String status;
}
