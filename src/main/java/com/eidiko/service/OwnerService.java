package com.eidiko.service;

import com.eidiko.dto.OwnerDTO;
import com.eidiko.entity.Owner;
import com.eidiko.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository ownerRepo;
    private final ModelMapper modelMapper;

    public OwnerDTO saveOwner(OwnerDTO ownerDTO) {
        Owner owner = modelMapper.map(ownerDTO, Owner.class);
        return modelMapper.map(ownerRepo.save(owner), OwnerDTO.class);
    }
}
