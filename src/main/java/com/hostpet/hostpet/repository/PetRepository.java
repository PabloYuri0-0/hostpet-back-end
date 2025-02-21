package com.hostpet.hostpet.repository;

import com.hostpet.hostpet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
    
}
