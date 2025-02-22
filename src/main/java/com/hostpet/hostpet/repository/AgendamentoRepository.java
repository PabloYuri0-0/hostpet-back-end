package com.hostpet.hostpet.repository;

import com.hostpet.hostpet.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    // Aqui podemos adicionar consultas personalizadas, se necessário
}
