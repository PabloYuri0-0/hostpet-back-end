package com.hostpet.hostpet.repository;

import com.hostpet.hostpet.entity.ContaHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaHotelRepository extends JpaRepository<ContaHotel, Long> {
    Optional<ContaHotel> findByUserId(Long userId);
}