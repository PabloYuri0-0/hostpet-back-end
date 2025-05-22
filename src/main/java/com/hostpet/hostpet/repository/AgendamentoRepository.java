package com.hostpet.hostpet.repository;

import com.hostpet.hostpet.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    // Aqui podemos adicionar consultas personalizadas, se necessário
    List<Agendamento> findByUserId(Long userId);


    @Override
    Optional<Agendamento> findById(Integer integer);


    @Query("SELECT COALESCE(SUM(a.valor), 0) FROM Agendamento a WHERE a.statusPagamento = 'PAGO'")
    BigDecimal getTotalPago();

    @Query("SELECT COALESCE(SUM(a.valor), 0) FROM Agendamento a WHERE a.statusPagamento = 'PAGO' AND a.dataAgendamento BETWEEN :inicio AND :fim")
    BigDecimal getTotalPagoEntreDatas(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);

    @Query("SELECT COUNT(a) FROM Agendamento a WHERE a.dataHoraInicio >= :start AND a.dataHoraInicio < :end AND a.user.id = :userId")
    Integer getCheckinsHoje(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("userId") Long userId);

    @Query("SELECT COUNT(a) FROM Agendamento a WHERE a.dataHoraFim >= :start AND a.dataHoraFim < :end AND a.user.id = :userId")
    Integer getCheckoutsHoje(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("userId") Long userId);


    @Query("SELECT COUNT(a) FROM Agendamento a WHERE a.dataHoraInicio >= :start AND a.dataHoraInicio < :end AND a.user.id = :userId")
    Integer getTotalHospedadoHotel(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("userId") Long userId);

    @Query("SELECT COUNT(DISTINCT a.baia.id) " +
            "FROM Agendamento a " +
            "WHERE a.user.id = :userId " +
            "AND a.dataHoraInicio <= :fim " +
            "AND a.dataHoraFim >= :inicio")
    int countBaiasOcupadasEntreDatas(
            @Param("userId") Long userId,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim
    );


    @Query(
            """
            SELECT COALESCE(SUM(a.valor), 0)
            FROM Agendamento a
            WHERE a.user.id = :userId
            """
    )
    BigDecimal getTotalEntrada (@Param("userId") Long userId);




}
