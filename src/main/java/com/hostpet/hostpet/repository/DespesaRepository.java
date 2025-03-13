package com.hostpet.hostpet.repository;

import com.hostpet.hostpet.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
}