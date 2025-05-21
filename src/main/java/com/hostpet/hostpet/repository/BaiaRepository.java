package com.hostpet.hostpet.repository;

import com.hostpet.hostpet.entity.Baia;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BaiaRepository extends JpaRepository<Baia, Integer> {

    // Contagem por status: LIVRE ou OCUPADA
    long countByUserIdAndStatus(Long userId, String status);

    // Contagem por limpeza: LIMPA ou SUJA
    long countByUserIdAndLimpeza(Long userId, String limpeza);

    // Buscar todas as baias por usuário
    List<Baia> findByUserId(Long userId);

    // Contar baias disponíveis (status = LIVRE e sem agendamento ativo no momento)
    @Query("""
        SELECT COUNT(b) 
        FROM Baia b 
        WHERE b.status = 'LIVRE'
        AND b.user.id = :userId
        AND NOT EXISTS (
            SELECT 1 FROM Agendamento a 
            WHERE a.baia = b 
            AND :now BETWEEN a.dataHoraInicio AND a.dataHoraFim
        )
    """)
    Integer countBaiasDisponiveis(@Param("now") LocalDateTime now, @PathParam("userId") Long userId);

    // Contar baias ocupadas (existe agendamento ativo no momento)
    @Query("""
        SELECT COUNT(b) 
        FROM Baia b 
        WHERE EXISTS (
            SELECT 1 FROM Agendamento a 
            WHERE a.baia = b 
            AND :now BETWEEN a.dataHoraInicio AND a.dataHoraFim
        ) AND b.user.id = :userId
    """)
    Integer countBaiasOcupadas(@Param("now") LocalDateTime now, @PathParam("userId") Long userId);

    @Query("""
        SELECT COUNT(b) 
        FROM Baia b 
        WHERE b.user.id = :userId
    """)
    Integer countBaiasTotais(@PathParam("userId") Long userId);


}
