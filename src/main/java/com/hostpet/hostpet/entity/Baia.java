package com.hostpet.hostpet.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "baias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Baia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private String status = "Livre";

    @OneToMany(mappedBy = "baia", cascade = CascadeType.ALL)
    private List<Agendamento> agendamentos;
}