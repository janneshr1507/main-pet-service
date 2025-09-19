package com.eidiko.service;

import com.eidiko.dto.SupplementsDTO;
import com.eidiko.entity.Supplements;
import com.eidiko.repository.SupplementsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplementsService {
    private final SupplementsRepository supplementsRepo;
    private final ModelMapper modelMapper;

    public SupplementsDTO bookSupplementsForPet(SupplementsDTO supplementsDTO) {
        Supplements supplements = modelMapper.map(supplementsDTO, Supplements.class);
        supplements.setDate(String.valueOf(LocalDate.now()));
        return modelMapper.map(supplementsRepo.save(supplements), SupplementsDTO.class);
    }

    public List<SupplementsDTO> getAllOrderedSupplements() {
        List<Supplements> supplements = supplementsRepo.findAll();
        List<SupplementsDTO> supplementsDTOS = new ArrayList<>();
        for(int i= 0; i< supplements.size(); i++) {
            Supplements supplement = supplements.get(i);
            supplementsDTOS.add(modelMapper.map(supplement, SupplementsDTO.class));
        }
        return supplementsDTOS;
    }
}
