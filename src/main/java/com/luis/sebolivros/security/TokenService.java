package com.luis.sebolivros.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    // Aqui fazemos uma string para diferencia nossos token dos demais
    // pois a crição deles pode ficar com o tempo facil de desifrar
    @Value("${jwt.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("sebolivros")
                    .withSubject(user.getUsername()) // Vai ser passador pro front end coloque id tbm e perfil
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException ex){
            throw new RuntimeException("Error while generating token", ex);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("sebolivros")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex){
            return "";
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.of("-03:00"));
    }
}
