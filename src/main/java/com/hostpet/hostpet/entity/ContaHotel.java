package com.hostpet.hostpet.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "conta_hotel")
public class ContaHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @JsonBackReference
    private User user;

    @Column(nullable = false)
    private BigDecimal saldo ;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // Adicione essa anotação
    private LocalDateTime ultimaAtualizacao = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }
}
