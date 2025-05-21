package com.hostpet.hostpet.dtos;

public class OcupacaoMensalDTO {
    private String mes;
    private Long percentualOcupacao;

    public OcupacaoMensalDTO(String mes, Long percentualOcupacao) {
        this.mes = mes;
        this.percentualOcupacao = percentualOcupacao;
    }

    public String getMes() {
        return mes;
    }

    public Long getPercentualOcupacao() {
        return percentualOcupacao;
    }
}
