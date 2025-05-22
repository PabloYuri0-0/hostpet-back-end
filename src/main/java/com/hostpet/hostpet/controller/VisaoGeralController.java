package com.hostpet.hostpet.controller;

import com.hostpet.hostpet.dtos.DetalhesSaldo;
import com.hostpet.hostpet.dtos.OcupacaoMensalDTO;
import com.hostpet.hostpet.dtos.VisaoGeralDTO;
import com.hostpet.hostpet.services.VisaoGeralService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/visao-geral")
public class VisaoGeralController {

    @Autowired
    private VisaoGeralService visaoGeralService;

    @GetMapping("/{userId}")
    public VisaoGeralDTO visaoGeral(@PathVariable("userId") Long userId ) {
        return visaoGeralService.visaoGeral(userId);
    }

    @GetMapping("/status-ocupacao/{userId}")
    public Long statusOcupacaoHotelByUser(@PathVariable("userId") Long userId){
        return  visaoGeralService.statusOcupacaoHotelByUser(userId);
    }
    @GetMapping("/ocupacao-mensal")
    public List<OcupacaoMensalDTO> getOcupacaoMensal(
            @RequestParam("userId") Long userId,
            @RequestParam(value = "ano", required = false) Integer ano
    ) {
        if (ano == null) {
            ano = LocalDate.now().getYear(); // define ano atual como padrão
        }
        return visaoGeralService.getEstatisticasMensaisOcupacao(userId, ano);
    }


    @GetMapping("/detalhes-saldo/{userId}")
    public DetalhesSaldo getDetalhesSaldo(@PathVariable("userId") Long userId) {
        return visaoGeralService.getDetalhesSaldo(userId);
    }


}
