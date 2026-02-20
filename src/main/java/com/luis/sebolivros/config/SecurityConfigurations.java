package com.luis.sebolivros.config;

import com.luis.sebolivros.security.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Configuration // Define a classe como de configuração
@EnableWebSecurity // Desativa o login feito pelo Spring Security, aquele que vem de base
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    @Autowired
    private Environment env;

    // Corrente de filtro de segurança,
    // fazemos a validação do usuario que fez um requisição usando metodos
    // por isso o nome filtro ele vai passar pelos metodos ate ser validado
    @Bean // <- Pro Spring instanciar nossa classe
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        if (Arrays.asList(env.getActiveProfiles()).contains("test")){
            httpSecurity.headers(headers -> headers.frameOptions
                    (frame -> frame.disable()));
        }
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Criamos uma configuração que não guardamos os dados sendo ela stateless usamos um token
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST,  "/auth/login", "/clientes", "/sebos").permitAll()
                        .requestMatchers(HttpMethod.POST, "/livros/**", "/autores/**", "/editora/**").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.PUT, "/livros/**", "/autores/**", "/editora/**").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.DELETE ,"/livros/**", "/autores/**", "/editora/**").hasRole("GESTOR")
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
         configuration.setAllowedOrigins(List.of("http://localhost:8080"));
         configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
         configuration.setAllowedHeaders(List.of("*"));
         configuration.setExposedHeaders(List.of("Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



    // Instancia a classe de authenticationConfiguration
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
