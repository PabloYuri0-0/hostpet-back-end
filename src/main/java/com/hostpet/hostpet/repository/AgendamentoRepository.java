package com.hostpet.hostpet.repository;

import com.hostpet.hostpet.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    List<Agendamento> findByUserId(Long userId);

    @Override
    Optional<Agendamento> findById(Integer integer);

    @Query("SELECT COALESCE(SUM(a.valor), 0) FROM Agendamento a WHERE a.statusPagamento = 'PAGO'")
    BigDecimal getTotalPago();

    @Query("SELECT COALESCE(SUM(a.valor), 0) FROM Agendamento a WHERE a.statusPagamento = 'PAGO' AND a.dataAgendamento BETWEEN :inicio AND :fim")
    BigDecimal getTotalPagoEntreDatas(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);

    // Checkins de hoje
    @Query("""
        SELECT COUNT(a)
        FROM Agendamento a
        WHERE a.checkin IS NOT NULL
        AND a.checkin >= :inicioDoDia
        AND a.checkin < :fimDoDia
        AND a.user.id = :userId
    """)
    Integer getCheckinsHoje(
            @Param("inicioDoDia") LocalDateTime inicioDoDia,
            @Param("fimDoDia") LocalDateTime fimDoDia,
            @Param("userId") Long userId
    );

    // Checkouts de hoje
    @Query("""
        SELECT COUNT(a)
        FROM Agendamento a
        WHERE a.checkOut IS NOT NULL
        AND a.checkOut >= :inicioDoDia
        AND a.checkOut < :fimDoDia
        AND a.user.id = :userId
    """)
    Integer getCheckoutsHoje(
            @Param("inicioDoDia") LocalDateTime inicioDoDia,
            @Param("fimDoDia") LocalDateTime fimDoDia,
            @Param("userId") Long userId
    );

    // Total hospedados no hotel AGORA
    @Query("""
        SELECT COUNT(a)
        FROM Agendamento a
        WHERE a.dataHoraInicio <= :agora
        AND a.dataHoraFim >= :agora
        AND a.user.id = :userId
    """)
    Integer getTotalHospedadoHotel(@Param("agora") LocalDateTime agora, @Param("userId") Long userId);

    @Query("""
        SELECT COUNT(DISTINCT a.baia.id)
        FROM Agendamento a
        WHERE a.user.id = :userId
        AND a.dataHoraInicio <= :fim
        AND a.dataHoraFim >= :inicio
    """)
    int countBaiasOcupadasEntreDatas(
            @Param("userId") Long userId,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim
    );

    @Query("""
        SELECT COALESCE(SUM(a.valor), 0)
        FROM Agendamento a
        WHERE a.user.id = :userId
    """)
    BigDecimal getTotalEntrada (@Param("userId") Long userId);
}
