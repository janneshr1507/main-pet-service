package com.eidiko.dto;

import com.eidiko.entity.Owner;
import com.eidiko.entity.Pet;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AppointmentDTO {
    private Long id;
    private Long petId;
    private Long ownerId;
    private String petName;
    private String date;
    private String time;
    private String location;
    private String status;
}
