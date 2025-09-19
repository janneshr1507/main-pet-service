package com.eidiko.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SupplementsDTO {
    private String petName;
    private List<String> selectedSupplements;
}
