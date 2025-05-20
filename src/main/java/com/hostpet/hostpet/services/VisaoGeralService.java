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


    public VisaoGeralDTO visaoGeral(Long userId){
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = start.plusDays(1);
        Integer countCheckIn = agendamentoRepository.getCheckinsHoje(start, end, userId);
        Integer countCheckOut = agendamentoRepository.getCheckoutsHoje(start, end,userId);
        Integer countTotalHospedadoHotel = agendamentoRepository.getTotalHospedadoHotel(start, end,userId);
        Integer countBaiasDisponiveis = baiaRepository.countBaiasDisponiveis(LocalDateTime.now(), userId);
        Integer countBaiasOcupadas = baiaRepository.countBaiasOcupadas(LocalDateTime.now(),userId);

        return new VisaoGeralDTO(countCheckIn, countCheckOut, countTotalHospedadoHotel, countBaiasDisponiveis, countBaiasOcupadas);
    }


    public Long  statusOcupacaoHotelByUser(Long userId){
        Integer countBaias = baiaRepository.countBaiasTotais(userId);
        Integer countBaiasOcupadas = baiaRepository.countBaiasOcupadas(LocalDateTime.now(),userId);

        double porcentagem = (countBaiasOcupadas * 100.0) / countBaias;
        return Math.round(porcentagem);
    }
}
