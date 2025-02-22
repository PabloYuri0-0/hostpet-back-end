package com.hostpet.hostpet.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private BigDecimal valor;
    private String formaPagamento = "Não Informado";
    private String statusPagamento = "Pendente";
    private LocalDateTime dataAgendamento;

    @ManyToOne
    @JoinColumn(name = "id_pet")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "id_Baia")


    private Baia baia;


    public Baia getBaia() {
        return baia;
    }

    public void setBaia(Baia baia) {
        this.baia = baia;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
