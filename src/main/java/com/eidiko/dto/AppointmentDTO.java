package com.eidiko.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AppointmentDTO {
    private String id;
    private String petName;
    private String date;
    private String time;
    private String location;
    private String status;
}
