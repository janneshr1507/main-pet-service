package com.eidiko.repository;

import com.eidiko.entity.Supplements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplementsRepository extends JpaRepository<Supplements, Long> {
    List<Supplements> findAllByOwnerId(long ownerId);
}
