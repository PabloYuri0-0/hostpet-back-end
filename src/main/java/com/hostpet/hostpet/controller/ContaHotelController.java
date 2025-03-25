package com.hostpet.hostpet.controller;

import com.hostpet.hostpet.entity.ContaHotel;
import com.hostpet.hostpet.entity.User;
import com.hostpet.hostpet.repository.UserRepository;
import com.hostpet.hostpet.services.ContaHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/conta-hotel")
public class ContaHotelController {

    @Autowired
    private ContaHotelService contaHotelService;

    @Autowired
    private UserRepository userRepository;

    // Endpoint para buscar o saldo do usuário
    @GetMapping("/{userId}")
    public ResponseEntity<ContaHotel> obterSaldo(@PathVariable Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);

        if (userOpt.isEmpty()) { // Correção aqui!
            return ResponseEntity.notFound().build();
        }

        User user = userOpt.get(); // Obtém o objeto User do Optional
        ContaHotel conta = contaHotelService.buscarPorUser(user);
        return ResponseEntity.ok(conta);
    }
}
