package com.hostpet.hostpet.dtos;

import java.math.BigDecimal;

public record DetalhesSaldo(BigDecimal saldoLiquido,
                            BigDecimal entrada,
                            BigDecimal saida) {
}
