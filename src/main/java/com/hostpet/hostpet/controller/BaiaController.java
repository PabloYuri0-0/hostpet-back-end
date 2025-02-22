package com.hostpet.hostpet.controller;

import com.hostpet.hostpet.entity.Baia;
import com.hostpet.hostpet.services.BaiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/baias")
public class BaiaController {

    @Autowired
    private BaiaService baiaService;

    // Endpoint para cadastrar uma nova baia
    @PostMapping
    public ResponseEntity<Baia> cadastrarBaia(@RequestBody Baia baia) {
        Baia novaBaia = baiaService.saveBaia(baia);
        return ResponseEntity.ok(novaBaia);
    }

    // Endpoint para listar todas as baias
    @GetMapping
    public ResponseEntity<List<Baia>> listarBaias() {
        List<Baia> baias = baiaService.getAllBaias();
        return ResponseEntity.ok(baias);
    }

    // Endpoint para excluir uma baia
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirBaia(@PathVariable Integer id) {
        baiaService.deleteBaia(id);
        return ResponseEntity.noContent().build();
    }
}
