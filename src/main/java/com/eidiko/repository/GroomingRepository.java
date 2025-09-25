package com.eidiko.repository;

import com.eidiko.entity.Grooming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroomingRepository extends JpaRepository<Grooming, Long> {
    List<Grooming> findAllByOwnerId(Long ownerId);
}
