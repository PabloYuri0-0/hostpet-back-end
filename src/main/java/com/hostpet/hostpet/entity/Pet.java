package com.hostpet.hostpet.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "pets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String sexo = "Indefinido";
    private String racaPet;
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @JsonBackReference
    private Cliente cliente;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private List<Agendamento> agendamentos;
}