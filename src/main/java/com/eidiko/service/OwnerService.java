package com.eidiko.service;

import com.eidiko.util.OwnerDetails;
import com.eidiko.dto.OwnerDTO;
import com.eidiko.entity.Owner;
import com.eidiko.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerService implements UserDetailsService {
    private final OwnerRepository ownerRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public OwnerDTO saveOwner(OwnerDTO ownerDTO) {
        Owner owner = modelMapper.map(ownerDTO, Owner.class);
        owner.setRole("ROLE_" + ownerDTO.getRole().toUpperCase());
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        return modelMapper.map(ownerRepo.save(owner), OwnerDTO.class);
    }

    public Optional<Owner> getOwner(Long id) {
        return ownerRepo.findById(id);
    }

    public UserDetails loadUserByUsername(String email) {
        Owner owner = ownerRepo.findByEmail(email);
        return new OwnerDetails(owner);
    }
}
