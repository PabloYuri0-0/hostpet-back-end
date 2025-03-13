package com.hostpet.hostpet.controller;

import com.hostpet.hostpet.entity.Despesa;
import com.hostpet.hostpet.services.DespesaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    private  DespesaService despesaService;

    @GetMapping
    public List<Despesa> listarTodas() {
        return despesaService.listarTodas();
    }

    @PostMapping
    public ResponseEntity<Despesa> criar(@RequestBody Despesa despesa) {
        return ResponseEntity.ok(despesaService.salvar(despesa));
    }

}