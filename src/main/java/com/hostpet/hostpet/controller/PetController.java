package com.hostpet.hostpet.controller;

import com.hostpet.hostpet.entity.Pet;
import com.hostpet.hostpet.services.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/pets")
@Validated
public class PetController {

    @Autowired
    private PetService petService;

    // Endpoint para cadastrar um novo Pet
    @PostMapping
    public ResponseEntity<Pet> cadastrarPet(@RequestBody @Valid Pet pet) {
        return ResponseEntity.ok(petService.savePet(pet));
    }

    // Endpoint para listar todos os Pets
    @GetMapping
    public ResponseEntity<List<Pet>> listarPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    // Endpoint para excluir um Pet
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPet(@PathVariable Integer id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}
