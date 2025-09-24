package com.eidiko.service;

import com.eidiko.controller.DashboardController;
import com.eidiko.dto.SupplementsDTO;
import com.eidiko.entity.Owner;
import com.eidiko.entity.Pet;
import com.eidiko.entity.Supplements;
import com.eidiko.repository.SupplementsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplementsService {
    private final OwnerService ownerService;
    private final PetService petService;
    private final SupplementsRepository supplementsRepo;
    private final ModelMapper modelMapper;
    private static final Logger log = LoggerFactory.getLogger(SupplementsService.class);

    public void bookSupplementsForPet(SupplementsDTO supplementsDTO) {
        Pet pet = petService.getById(supplementsDTO.getPetId())
                .orElseThrow(() -> new RuntimeException("Pet doesn't exists"));
        Owner owner = ownerService.getOwner(supplementsDTO.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner doesn't exists"));
        Supplements supplements = modelMapper.map(supplementsDTO, Supplements.class);
        supplements.setDate(String.valueOf(LocalDate.now()));
        supplements.setPet(pet);
        supplements.setOwner(owner);
        modelMapper.map(supplementsRepo.save(supplements), SupplementsDTO.class);
    }

    public List<SupplementsDTO> getAllOrderedSupplements() {
        List<Supplements> supplements = supplementsRepo.findAll();
        List<SupplementsDTO> supplementsDTOS = new ArrayList<>();
        for(int i= 0; i< supplements.size(); i++) {
            Supplements supplement = supplements.get(i);
            SupplementsDTO supplementsDTO = modelMapper.map(supplement, SupplementsDTO.class);
            supplementsDTO.setPetName(supplements.get(i).getPet().getName());
            supplementsDTOS.add(supplementsDTO);
        }
        return supplementsDTOS;
    }
}
