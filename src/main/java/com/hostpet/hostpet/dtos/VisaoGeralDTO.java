package com.hostpet.hostpet.dtos;

public class VisaoGeralDTO {
    public Integer countCheckIn;
    public Integer countCheckOut;
    public Integer countTotaHospedadoHotel;
    public Integer countBaiasDisponiveis;
    public Integer countBaiasOcupadas;

    public VisaoGeralDTO(Integer countCheckIn, Integer countCheckOut, Integer countTotaHospedadoHotel, Integer countBaiasDisponiveis, Integer countBaiasOcupadas) {
        this.countCheckIn = countCheckIn;
        this.countCheckOut = countCheckOut;
        this.countTotaHospedadoHotel = countTotaHospedadoHotel;
        this.countBaiasDisponiveis = countBaiasDisponiveis;
        this.countBaiasOcupadas = countBaiasOcupadas;
    }
}
