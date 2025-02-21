package com.hostpet.hostpet.services;

import com.hostpet.hostpet.entity.Cliente;
import com.hostpet.hostpet.entity.Pet;
import com.hostpet.hostpet.repository.PetRepository;
import com.hostpet.hostpet.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private IClienteRepository clienteRepository;

    public PetService(PetRepository petRepository, IClienteRepository clienteRepository) {
        this.petRepository = petRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Optional<Pet> getPetById(Integer id) {
        return petRepository.findById(id);
    }

    // Método para salvar o Pet e vinculá-lo ao Cliente
    public Pet savePet(Pet pet) {
        if (pet.getCliente() != null && pet.getCliente().getId() != null) {
            Optional<Cliente> clienteOpt = clienteRepository.findById(pet.getCliente().getId());
            if (clienteOpt.isEmpty()) {
                throw new IllegalArgumentException("Cliente não encontrado");
            }
            // Associa o cliente ao pet
            pet.setCliente(clienteOpt.get());
        } else {
            throw new IllegalArgumentException("Cliente não informado");
        }

        return petRepository.save(pet);
    }

    // Método para excluir um Pet
    public void deletePet(Integer id) {
        petRepository.deleteById(id);
    }
}
