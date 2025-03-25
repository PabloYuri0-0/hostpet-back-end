package com.hostpet.hostpet.controller;

import com.hostpet.hostpet.entity.Agendamento;
import com.hostpet.hostpet.entity.Cliente;
import com.hostpet.hostpet.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    // Rota para cadastrar um novo agendamento
    @PostMapping
    public ResponseEntity<Agendamento> cadastrarAgendamento(@RequestBody Agendamento agendamento) {
        Agendamento agendamentoSalvo = agendamentoService.salvarAgendamento(agendamento);
        return ResponseEntity.ok(agendamentoSalvo);
    }

    // Rota para listar todos os agendamentos
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Agendamento>> listAgendamentosByUser(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(agendamentoService.listAgendamentosByUser(userId));
    }

    // Rota para buscar um agendamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarAgendamentoPorId(@PathVariable Integer id) {
        Agendamento agendamento = agendamentoService.buscarAgendamentoPorId(id);
        return ResponseEntity.ok(agendamento);
    }

    // Rota para excluir um agendamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAgendamento(@PathVariable Integer id) {
        agendamentoService.excluirAgendamento(id);
        return ResponseEntity.noContent().build();
    }
}
