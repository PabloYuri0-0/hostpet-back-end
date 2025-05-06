package com.hostpet.hostpet.forms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hostpet.hostpet.enums.FormaPagamento;
import com.hostpet.hostpet.enums.StatusPagamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AgendamentoForm {

    @NotNull(message = "A data de início do agendamento é obrigatória    ")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHoraInicio;

    @NotNull(message = "A data de fim do agendamento é obrigatória")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHoraFim;


    @NotNull(message = "O valor do agendamento é obrigatório")
    private BigDecimal valor;

    @NotNull(message = "A forma de pagamento é obrigatória")
    private FormaPagamento formaPagamento;

    @NotNull(message = "O id do pet é obrigatório")
    private Integer idPet;

    @NotNull(message = "O id da baia é obrigatório")
    private Integer idBaia;

    @NotNull(message = "O id do usuário é obrigatório")
    private Long userId;


    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getIdPet() {
        return idPet;
    }

    public void setIdPet(Integer idPet) {
        this.idPet = idPet;
    }

    public Integer getIdBaia() {
        return idBaia;
    }

    public void setIdBaia(Integer idBaia) {
        this.idBaia = idBaia;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
