package com.hostpet.hostpet.dtos;

import com.hostpet.hostpet.entity.Pet;

import java.time.LocalDate;

public class PetsListDTO {
    private Integer id;
    private String nome;
    private String sexo;
    private String racaPet;
    private String observacoes;
    private LocalDate dtNascimento;
    private Long clienteId;
    private String nomeDono;

    public PetsListDTO() {
    }

    public PetsListDTO(Pet pet) {
        this.id = pet.getId();
        this.nome = pet.getNome();
        this.sexo = pet.getSexo();
        this.racaPet = pet.getRacaPet();
        this.observacoes = pet.getObservacoes();
        this.dtNascimento = pet.getDtNascimento();
        this.clienteId = pet.getCliente().getId();
        this.nomeDono = pet.getCliente().getNome();
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRacaPet() {
        return racaPet;
    }

    public void setRacaPet(String racaPet) {
        this.racaPet = racaPet;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getNomeDono() {
        return nomeDono;
    }

    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
    }
}