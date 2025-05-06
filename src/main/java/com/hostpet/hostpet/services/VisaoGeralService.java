package com.hostpet.hostpet.services;

import com.hostpet.hostpet.dtos.VisaoGeralDTO;
import org.springframework.stereotype.Service;

@Service
public class VisaoGeralService {

    public VisaoGeralDTO visaoGeral(){
        Integer countCheckIn = 10;
        Integer countCheckOut = 5;
        Integer countTotaHospedadoHotel = 20;
        Integer countBaiasDisponiveis = 15;
        Integer countBaiasOcupadas = 5;

        return new VisaoGeralDTO(countCheckIn, countCheckOut, countTotaHospedadoHotel, countBaiasDisponiveis, countBaiasOcupadas);
    }
}
