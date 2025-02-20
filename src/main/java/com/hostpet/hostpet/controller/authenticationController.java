package com.hostpet.hostpet.controller;


import com.hostpet.hostpet.entity.AuthenticationDTO;
import com.hostpet.hostpet.entity.RegisterDTO;
import com.hostpet.hostpet.entity.User;
import com.hostpet.hostpet.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class authenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(),data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if (this.userRepository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().build();

        String encryptadPassword = new BCryptPasswordEncoder().encode(data.password());

        User newUser = new User(data.username(),data.email(),encryptadPassword,data.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();

    }

}
