package com.hostpet.hostpet.services;

import com.hostpet.hostpet.entity.Despesa;
import com.hostpet.hostpet.repository.DespesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DespesaService {

    private DespesaRepository despesaRepository;


    public List<Despesa> listarTodas() {
        return despesaRepository.findAll();
    }

    public Optional<Despesa> buscarPorId(Long id) {
        return despesaRepository.findById(id);
    }

    public Despesa salvar(Despesa despesa) {
        return despesaRepository.save(despesa);
    }

    public void deletar(Long id) {
        despesaRepository.deleteById(id);
    }
}