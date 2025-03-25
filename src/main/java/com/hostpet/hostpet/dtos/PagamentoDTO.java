package com.hostpet.hostpet.dtos;

import java.math.BigDecimal;

public class PagamentoDTO {

    private BigDecimal valorPago;
    private String formaPagamento;

    // Getters and Setters
    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
