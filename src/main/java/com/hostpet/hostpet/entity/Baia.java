package com.hostpet.hostpet.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<Agendamento> agendamentos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}