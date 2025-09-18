package com.eidiko.service;

import com.eidiko.dto.PetDTO;
import com.eidiko.entity.Pet;
import com.eidiko.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepo;
    private final ModelMapper modelMapper;

    public PetDTO savePet(PetDTO petDTO) {
        Pet newPet = modelMapper.map(petDTO, Pet.class);
        return modelMapper.map(petRepo.save(newPet), PetDTO.class);
    }

    public List<Pet> getAllPets() {
        return petRepo.findAll();
    }
}
