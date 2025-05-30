package com.hostpet.hostpet.services;

import com.hostpet.hostpet.dtos.FinanceiroEntradaDTO;
import com.hostpet.hostpet.entity.Agendamento;
import com.hostpet.hostpet.entity.Baia;
import com.hostpet.hostpet.entity.Pet;
import com.hostpet.hostpet.entity.User;
import com.hostpet.hostpet.enums.StatusPagamento;
import com.hostpet.hostpet.forms.AgendamentoForm;
import com.hostpet.hostpet.repository.AgendamentoRepository;
import com.hostpet.hostpet.repository.BaiaRepository;
import com.hostpet.hostpet.repository.PetRepository;
import com.hostpet.hostpet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
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
        agendamento.setFormaPagamento(form.getFormaPagamento().toString());
        agendamento.setStatusPagamento(StatusPagamento.PENDENTE.toString());
        agendamento.setDataAgendamento(LocalDateTime.now());
        agendamento.setPet(petOpt.get());
        agendamento.setBaia(baiaOpt.get());
        agendamento.setUser(userOpt.get());

        return agendamentoRepository.save(agendamento);
    }

    public FinanceiroEntradaDTO getTotaisPagos() {
        LocalDateTime hojeInicio = LocalDate.now().atStartOfDay();
        LocalDateTime hojeFim = hojeInicio.plusDays(1).minusSeconds(1);

        LocalDateTime inicioMes = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        LocalDateTime fimMes = inicioMes.plusMonths(1).minusSeconds(1);

        LocalDate hoje = LocalDate.now();
        DayOfWeek primeiroDiaSemana = DayOfWeek.SUNDAY;
        LocalDate inicioSemana = hoje.with(TemporalAdjusters.previousOrSame(primeiroDiaSemana));
        LocalDate fimSemana = inicioSemana.plusDays(6);

        return new FinanceiroEntradaDTO(
                agendamentoRepository.getTotalPago(),
                agendamentoRepository.getTotalPagoEntreDatas(inicioMes, fimMes),
                agendamentoRepository.getTotalPagoEntreDatas(inicioSemana.atStartOfDay(), fimSemana.atTime(23, 59, 59)),
                agendamentoRepository.getTotalPagoEntreDatas(hojeInicio, hojeFim)
        );
    }



    public Agendamento realizarCheckin(Integer agendamentoId) {
        Optional<Agendamento> agendamentoOpt = agendamentoRepository.findById(agendamentoId);
        if (agendamentoOpt.isEmpty()) {
            throw new IllegalArgumentException("Agendamento não encontrado.");
        }

        Agendamento agendamento = agendamentoOpt.get();

        // Verifica se a baia vinculada existe
        Baia baia = agendamento.getBaia();
        if (baia == null) {
            throw new IllegalStateException("Baia vinculada ao agendamento não encontrada.");
        }

        // Faz as alterações apenas depois das validações
        agendamento.setCheckin(LocalDateTime.now());
        agendamento.setStatusPagamento(StatusPagamento.PAGO.toString());

        baia.setStatus("OCUPADA");
        baia.setLimpeza("SUJA");

        baiaRepository.save(baia);
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento realizarCheckOut(Integer agendamentoId) {
        Agendamento agendamento = agendamentoRepository.findById(agendamentoId)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));


        agendamento.setCheckOut(LocalDateTime.now());


        Baia baia = agendamento.getBaia();
        if (baia != null) {
            baia.setStatus("LIVRE");

            baiaRepository.save(baia);
        }

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



    public void excluirAgendamento(Integer agendamentoId) {
        Agendamento agendamento = agendamentoRepository.findById(agendamentoId)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));


        agendamentoRepository.delete(agendamento);
    }
}
