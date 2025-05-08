package com.hostpet.hostpet.services;

import com.hostpet.hostpet.dtos.VisaoGeralDTO;
import com.hostpet.hostpet.repository.AgendamentoRepository;
import com.hostpet.hostpet.repository.BaiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class VisaoGeralService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private BaiaRepository baiaRepository;


    public VisaoGeralDTO visaoGeral(){
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = start.plusDays(1);
        Integer countCheckIn = agendamentoRepository.getCheckinsHoje(start, end);
        Integer countCheckOut = agendamentoRepository.getCheckoutsHoje(start, end);
        Integer countTotalHospedadoHotel = agendamentoRepository.getTotalHospedadoHotel(start, end);
        Integer countBaiasDisponiveis = baiaRepository.countBaiasDisponiveis(LocalDateTime.now());
        Integer countBaiasOcupadas = baiaRepository.countBaiasOcupadas(LocalDateTime.now());

        return new VisaoGeralDTO(countCheckIn, countCheckOut, countTotalHospedadoHotel, countBaiasDisponiveis, countBaiasOcupadas);
    }
}
