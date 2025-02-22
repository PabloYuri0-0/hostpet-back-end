package com.hostpet.hostpet.repository;

import com.hostpet.hostpet.entity.Baia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaiaRepository extends JpaRepository<Baia, Integer> {
    // Você pode adicionar métodos personalizados aqui se necessário
}
