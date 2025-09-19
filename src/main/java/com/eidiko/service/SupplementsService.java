package com.eidiko.service;

import com.eidiko.dto.SupplementsDTO;
import com.eidiko.entity.Supplements;
import com.eidiko.repository.SupplementsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplementsService {
    private final SupplementsRepository supplementsRepo;
    private final ModelMapper modelMapper;

    public SupplementsDTO bookSupplementsForPet(SupplementsDTO supplementsDTO) {
        Supplements supplements = modelMapper.map(supplementsDTO, Supplements.class);
        return modelMapper.map(supplementsRepo.save(supplements), SupplementsDTO.class);
    }
}
