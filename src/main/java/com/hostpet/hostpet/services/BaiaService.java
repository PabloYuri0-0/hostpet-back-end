package com.hostpet.hostpet.services;

import com.hostpet.hostpet.entity.Baia;
import com.hostpet.hostpet.repository.BaiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaiaService {

    @Autowired
    private BaiaRepository baiaRepository;

    // Método para salvar a baia
    public Baia saveBaia(Baia baia) {
        return baiaRepository.save(baia);
    }

    // Método para listar todas as baias
    public List<Baia> getAllBaias() {
        return baiaRepository.findAll();
    }

    // Método para excluir a baia
    public void deleteBaia(Integer id) {
        baiaRepository.deleteById(id);
    }
}
