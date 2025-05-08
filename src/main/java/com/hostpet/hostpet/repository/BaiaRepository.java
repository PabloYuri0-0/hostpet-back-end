package com.hostpet.hostpet.repository;

import com.hostpet.hostpet.entity.Baia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BaiaRepository extends JpaRepository<Baia, Integer> {
    long countByUserIdAndStatus(Long userId, String status);
    List<Baia> findByUserId(Long userId);


    // Método para contar o número de baias disponíveis
    @Query("""
    SELECT COUNT(b) 
    FROM Baia b 
    WHERE b.status = 'Livre'
    AND NOT EXISTS (
        SELECT 1 FROM Agendamento a 
        WHERE a.baia = b 
        AND :now BETWEEN a.dataHoraInicio AND a.dataHoraFim
    )
""")
    Integer countBaiasDisponiveis(@Param("now") LocalDateTime now);

    // Método para contar o número de baias ocupadas
    @Query("""
    SELECT COUNT(b) 
    FROM Baia b 
    WHERE EXISTS (
        SELECT 1 FROM Agendamento a 
        WHERE a.baia = b 
        AND :now BETWEEN a.dataHoraInicio AND a.dataHoraFim
    )
""")
    Integer countBaiasOcupadas(@Param("now") LocalDateTime now);
}
