package com.hostpet.hostpet.services;

import com.hostpet.hostpet.entity.Agendamento;
import com.hostpet.hostpet.entity.Baia;
import com.hostpet.hostpet.entity.Pet;
import com.hostpet.hostpet.entity.User;
import com.hostpet.hostpet.repository.AgendamentoRepository;
import com.hostpet.hostpet.repository.BaiaRepository;
import com.hostpet.hostpet.repository.PetRepository;
import com.hostpet.hostpet.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

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

        // Verificar se o usuário  existe
        Optional<User> userOpt = userRepository.findById(agendamento.getUser().getId());
        if (!userOpt.isPresent()) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }
        agendamento.setUser(userOpt.get());

        // Salvar o agendamento no banco
        return agendamentoRepository.save(agendamento);
    }

    // Método para listar todos os agendamentos
    public List<Agendamento> listAgendamentosByUser(Long userId) {
        // Verifica se o usuário existe
        boolean usuarioExiste = userRepository.existsById(userId);
        if (!usuarioExiste) {
            throw new IllegalArgumentException("Usuário não encontrado ou não cadastrado.");
        }
        return agendamentoRepository.findByUserId(userId);
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
