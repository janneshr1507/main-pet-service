package com.eidiko.repository;

import com.eidiko.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Pet findByName(String name);
    List<Pet> findAllByOwnerId(Long ownerId);
}
