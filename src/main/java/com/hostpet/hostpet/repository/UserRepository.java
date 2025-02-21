package com.hostpet.hostpet.repository;

import com.hostpet.hostpet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Método para buscar um usuário pelo seu username
     UserDetails findByUsername(String username);

    // Método para buscar um usuário pelo seu email
    Optional<User> findByEmail(String email);

    // Você pode adicionar outros métodos de busca conforme necessário
}
