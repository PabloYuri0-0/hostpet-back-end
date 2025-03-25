package com.hostpet.hostpet.services;

import com.hostpet.hostpet.entity.ContaHotel;
import com.hostpet.hostpet.entity.User;
import com.hostpet.hostpet.repository.ContaHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ContaHotelService {

    @Autowired
    private ContaHotelRepository contaHotelRepository;

    public ContaHotel buscarPorUser(User user) {
        return contaHotelRepository.findByUserId(user.getId()) // Agora está mais claro
                .orElseGet(() -> {
                    ContaHotel novaConta = new ContaHotel();
                    novaConta.setUser(user);
                    novaConta.setSaldo(BigDecimal.ZERO);
                    return contaHotelRepository.save(novaConta);
                });
    }

    public void adicionarEntrada(User user, BigDecimal valor) {
        ContaHotel conta = buscarPorUser(user);
        conta.setSaldo(conta.getSaldo().add(valor)); // Adiciona ao saldo atual
        conta.setUltimaAtualizacao(LocalDateTime.now());
        contaHotelRepository.save(conta);
    }
}
