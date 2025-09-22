package com.eidiko.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class OwnerDTO {
    private String name;
    private String email;
    private String contact;
    private String password;
    private String role;
}
