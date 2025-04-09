package com.hostpet.hostpet.services;

import com.hostpet.hostpet.entity.Agendamento;
import com.hostpet.hostpet.entity.Baia;
import com.hostpet.hostpet.entity.Pet;
import com.hostpet.hostpet.entity.User;
import com.hostpet.hostpet.forms.AgendamentoForm;
import com.hostpet.hostpet.repository.AgendamentoRepository;
import com.hostpet.hostpet.repository.BaiaRepository;
import com.hostpet.hostpet.repository.PetRepository;
import com.hostpet.hostpet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Agendamento saveAgendamento(AgendamentoForm form) {
        Optional<Pet> petOpt = petRepository.findById(form.getIdPet());
        if (petOpt.isEmpty()) {
            throw new IllegalArgumentException("Pet não encontrado.");
        }

        Optional<Baia> baiaOpt = baiaRepository.findById(form.getIdBaia());
        if (baiaOpt.isEmpty()) {
            throw new IllegalArgumentException("Baia não encontrada.");
        }

        Optional<User> userOpt = userRepository.findById(form.getUserId());
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setDataHoraInicio(form.getDataHoraInicio());
        agendamento.setDataHoraFim(form.getDataHoraFim());
        agendamento.setValor(form.getValor());
        agendamento.setFormaPagamento(form.getFormaPagamento());
        agendamento.setStatusPagamento(form.getStatusPagamento());
        agendamento.setDataAgendamento(LocalDateTime.now());
        agendamento.setPet(petOpt.get());
        agendamento.setBaia(baiaOpt.get());
        agendamento.setUser(userOpt.get());

        return agendamentoRepository.save(agendamento);
    }



    public List<Agendamento> listAgendamentosByUser(Long userId) {
        boolean usuarioExiste = userRepository.existsById(userId);
        if (!usuarioExiste) {
            throw new IllegalArgumentException("Usuário não encontrado ou não cadastrado.");
        }
        return agendamentoRepository.findByUserId(userId);
    }

    public Agendamento buscarAgendamentoPorId(Integer id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));
    }

    public void excluirAgendamento(Integer id) {
        agendamentoRepository.deleteById(id);
    }
}
