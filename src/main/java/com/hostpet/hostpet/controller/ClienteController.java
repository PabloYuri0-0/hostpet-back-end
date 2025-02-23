package com.hostpet.hostpet.controller;

import com.hostpet.hostpet.entity.Cliente;
import com.hostpet.hostpet.forms.ClienteForm;
import com.hostpet.hostpet.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Validated
public class ClienteController {
    @Autowired
    private ClienteService clienteService;


    @PostMapping("/auth/register")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody @Valid ClienteForm clienteForm) {
        return ResponseEntity.ok(clienteService.cadastrarCliente(clienteForm));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        clienteService.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }
}