package com.eidiko.service;

import com.eidiko.dto.GroomingDTO;
import com.eidiko.entity.Grooming;
import com.eidiko.entity.Owner;
import com.eidiko.entity.Pet;
import com.eidiko.repository.GroomingRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroomingService {
    private final OwnerService ownerService;
    private final PetService petService;
    private final GroomingRepository groomingRepo;
    private final ModelMapper modelMapper;

    public void saveGroomingSchedule(GroomingDTO groomingDTO) {
        Pet pet = petService.getById(groomingDTO.getPetId())
                .orElseThrow(() -> new RuntimeException("Pet doesn't exists"));
        Owner owner = ownerService.getOwner(groomingDTO.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner doesn't exists"));
        Grooming grooming = modelMapper.map(groomingDTO, Grooming.class);
        grooming.setPet(pet);
        grooming.setOwner(owner);
        grooming.setStatus("Pending");
        modelMapper.map(groomingRepo.save(grooming), GroomingDTO.class);
    }

    public List<GroomingDTO> getAllGroomingSchedules() {
        List<Grooming> groomingScheduleList = groomingRepo.findAll();
        List<GroomingDTO> groomingDTOScheduleList = new ArrayList<>();
        for(int i=0; i<groomingScheduleList.size(); i++) {
            GroomingDTO groomingDTO = modelMapper.map(groomingScheduleList.get(i), GroomingDTO.class);
            groomingDTOScheduleList.add(groomingDTO);
        }
        return groomingDTOScheduleList;
    }
}
