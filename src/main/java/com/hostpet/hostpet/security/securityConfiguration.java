package com.hostpet.hostpet.security;

import com.hostpet.hostpet.config.CustomCorsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class securityConfiguration {

    @Autowired
    // Filtro de segurança customizado para autenticação
    SecurityFilter securityFilter;

    @Autowired
    CustomCorsConfiguration customCorsConfiguration;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // Configurações de segurança HTTP
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // Desabilita a proteção CSRF, pois estamos utilizando autenticação baseada em token
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Define a política de sessão para sem estado (sem cookies de sessão)
                .authorizeHttpRequests(authorize -> authorize // Configura as permissões de acesso
                        .requestMatchers("/h2-console/**").permitAll() // Permite acesso ao console do H2
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST,"/createUser").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/baia").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/pet").hasRole("ADMIN")
                        .anyRequest().authenticated() // Exige autenticação para todas as outras requisições
                )
                .cors(cors -> cors.configurationSource(customCorsConfiguration)) // Configuração de CORS personalizada
                .headers(headers -> headers.frameOptions().sameOrigin())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)// Adiciona o filtro de segurança customizado antes do filtro padrão de autenticação
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
         // Cria o AuthenticationManager a partir da configuração de autenticação
        return  authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
         // Cria um codificador de senhas BCrypt, para garantir que as senhas sejam armazenadas de forma segura
        return  new BCryptPasswordEncoder();
    }
}
