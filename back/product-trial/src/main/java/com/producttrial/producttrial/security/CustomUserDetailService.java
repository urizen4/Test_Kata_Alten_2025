package com.producttrial.producttrial.security;

import com.producttrial.producttrial.model.User;
import com.producttrial.producttrial.repository.UserRepository;
import com.producttrial.producttrial.services.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    // Utilise l'email pour chercher un utilisateur
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                //Ici on gère de manière dynamique le role correspondant avec email admin@admin.com
                // avec une opération ternaire
                .roles(user.getEmail().equals("admin@admin.com") ? "ADMIN" : "USER")
                .build();
    }
}
