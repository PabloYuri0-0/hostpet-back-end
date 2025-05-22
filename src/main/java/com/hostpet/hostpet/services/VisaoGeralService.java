package com.hostpet.hostpet.services;

import com.hostpet.hostpet.dtos.DetalhesSaldo;
import com.hostpet.hostpet.dtos.OcupacaoMensalDTO;
import com.hostpet.hostpet.dtos.VisaoGeralDTO;
import com.hostpet.hostpet.repository.AgendamentoRepository;
import com.hostpet.hostpet.repository.BaiaRepository;
import com.hostpet.hostpet.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service
public class VisaoGeralService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private DespesaRepository despesaRepository;
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

    public List<OcupacaoMensalDTO> getEstatisticasMensaisOcupacao(Long userId, int ano) {
        List<OcupacaoMensalDTO> estatisticas = new ArrayList<>();

        int totalBaias = baiaRepository.countBaiasTotais(userId);
        if (totalBaias == 0) return Collections.emptyList();

        for (int mes = 1; mes <= 12; mes++) {
            LocalDate inicioMes = LocalDate.of(ano, mes, 1);
            LocalDate fimMes = inicioMes.withDayOfMonth(inicioMes.lengthOfMonth());

            int baiasOcupadas = agendamentoRepository.countBaiasOcupadasEntreDatas(
                    userId,
                    inicioMes.atStartOfDay(),
                    fimMes.atTime(LocalTime.MAX)
            );

            long percentual = Math.round((baiasOcupadas * 100.0) / totalBaias);
            estatisticas.add(new OcupacaoMensalDTO(getNomeMes(mes), percentual));
        }

        return estatisticas;
    }

    private String getNomeMes(int mes) {
        return Month.of(mes).getDisplayName(TextStyle.SHORT, new Locale("pt", "BR"));
    }


    public DetalhesSaldo getDetalhesSaldo(Long userId) {
        BigDecimal entrada = agendamentoRepository.getTotalEntrada(userId);
        BigDecimal despesas = despesaRepository.getTotalDespesas(userId);
        BigDecimal saldo = entrada.subtract(despesas);

         DetalhesSaldo detalhesSaldo = new DetalhesSaldo(
                 saldo,
                 entrada,
                 despesas
         );
        return detalhesSaldo;
    }
}
