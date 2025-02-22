package com.hostpet.hostpet.services;

import com.hostpet.hostpet.entity.Agendamento;
import com.hostpet.hostpet.entity.Baia;
import com.hostpet.hostpet.entity.Pet;
import com.hostpet.hostpet.repository.AgendamentoRepository;
import com.hostpet.hostpet.repository.BaiaRepository;
import com.hostpet.hostpet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private BaiaRepository baiaRepository;

    // Método para salvar o agendamento
    public Agendamento salvarAgendamento(Agendamento agendamento) {
        // Verificar se o pet existe
        Optional<Pet> petOpt = petRepository.findById(agendamento.getPet().getId());
        if (!petOpt.isPresent()) {
            throw new IllegalArgumentException("Pet não encontrado.");
        }
        agendamento.setPet(petOpt.get());

        // Verificar se a baia existe
        Optional<Baia> baiaOpt = baiaRepository.findById(agendamento.getBaia().getId());
        if (!baiaOpt.isPresent()) {
            throw new IllegalArgumentException("Baia não encontrada.");
        }
        agendamento.setBaia(baiaOpt.get());

        // Salvar o agendamento no banco
        return agendamentoRepository.save(agendamento);
    }

    // Método para listar todos os agendamentos
    public List<Agendamento> listarAgendamentos() {
        return agendamentoRepository.findAll();
    }

    // Método para buscar um agendamento por ID
    public Agendamento buscarAgendamentoPorId(Integer id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));
    }

    // Método para excluir um agendamento
    public void excluirAgendamento(Integer id) {
        agendamentoRepository.deleteById(id);
    }
}
