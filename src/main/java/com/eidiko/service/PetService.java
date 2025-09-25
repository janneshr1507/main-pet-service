package com.eidiko.service;

import com.eidiko.dto.PetDTO;
import com.eidiko.entity.Owner;
import com.eidiko.entity.Pet;
import com.eidiko.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepo;
    private final ModelMapper modelMapper;
    private final OwnerService ownerService;

    public PetDTO savePet(PetDTO petDTO) {
        Owner owner = ownerService.getOwner(petDTO.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner does not exists"));
        Pet newPet = modelMapper.map(petDTO, Pet.class);
        newPet.setOwner(owner);
        return modelMapper.map(petRepo.save(newPet), PetDTO.class);
    }

    public List<Pet> getAllPets(Long ownerId) {
        return petRepo.findAllByOwnerId(ownerId);
    }

    public Pet getByName(String name) {
        return petRepo.findByName(name);
    }

    public Optional<Pet> getById(Long id) {
        return petRepo.findById(id);
    }
}
