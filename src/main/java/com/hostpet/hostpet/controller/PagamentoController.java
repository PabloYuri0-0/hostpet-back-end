package com.hostpet.hostpet.controller;

import com.hostpet.hostpet.entity.Agendamento;
import com.hostpet.hostpet.entity.User;
import com.hostpet.hostpet.repository.AgendamentoRepository;
import com.hostpet.hostpet.services.ContaHotelService;
import com.hostpet.hostpet.dtos.PagamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ContaHotelService contaHotelService;

    // Endpoint para realizar o pagamento do agendamento
    @PostMapping("/{agendamentoId}")
    public ResponseEntity<String> realizarPagamento(
            @PathVariable Integer agendamentoId,
            @RequestBody PagamentoDTO pagamentoDTO) {

        // Buscar o agendamento pelo ID usando Optional
        Optional<Agendamento> agendamentoOpt = agendamentoRepository.findById(agendamentoId);

        // Verificar se o agendamento existe e está pendente
        if (agendamentoOpt.isEmpty() || !agendamentoOpt.get().getStatusPagamento().equals("Pendente")) {
            return ResponseEntity.badRequest().body("Agendamento não encontrado ou já foi pago.");
        }

        Agendamento agendamento = agendamentoOpt.get(); // Obtém o agendamento do Optional

        // Alterar o status do agendamento para "Pago"
        agendamento.setStatusPagamento("Pago");
        agendamento.setFormaPagamento(pagamentoDTO.getFormaPagamento());

        // Salvar o agendamento atualizado
        agendamentoRepository.save(agendamento);

        // Atualizar o saldo da conta do hotel
        User user = agendamento.getUser(); // O usuário dono do hotel
        contaHotelService.adicionarEntrada(user, pagamentoDTO.getValorPago()); // Adiciona o valor pago ao saldo

        return ResponseEntity.ok("Pagamento realizado com sucesso!");
    }
}
