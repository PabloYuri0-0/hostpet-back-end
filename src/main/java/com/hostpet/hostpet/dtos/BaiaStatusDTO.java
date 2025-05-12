package com.hostpet.hostpet.dtos;

public class BaiaStatusDTO {
    private long totalLivres;
    private long totalOcupadas;
    private long totalLimpas;
    private long totalSujas;

    public BaiaStatusDTO(long totalLivres, long totalOcupadas, long totalLimpas, long totalSujas) {
        this.totalLivres = totalLivres;
        this.totalOcupadas = totalOcupadas;
        this.totalLimpas = totalLimpas;
        this.totalSujas = totalSujas;
    }

    public long getTotalLivres() {
        return totalLivres;
    }

    public long getTotalOcupadas() {
        return totalOcupadas;
    }

    public long getTotalLimpas() {
        return totalLimpas;
    }

    public long getTotalSujas() {
        return totalSujas;
    }
}
