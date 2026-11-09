package com.pagina_web.backend.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable()) // Desactivar CSRF para poder probar POSTs en Swagger fácilmente
                    .authorizeHttpRequests(auth -> auth
                            // Permitir acceso sin contraseña UNICAMENTE a Swagger y OpenAPI
                            .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                            // Cualquier otra ruta del backend (ej. tus controladores) pedirá autenticación
                            .anyRequest().authenticated()
                    );
            return http.build();
        }
}

