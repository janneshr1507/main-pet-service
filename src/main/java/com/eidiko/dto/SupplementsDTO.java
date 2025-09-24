package com.eidiko.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
public class SupplementsDTO {
    private Long id;
    private Long petId;
    private String petName;
    private Long ownerId;
    private List<String> selectedSupplements;
    private String quantity;
    private String date;
}
