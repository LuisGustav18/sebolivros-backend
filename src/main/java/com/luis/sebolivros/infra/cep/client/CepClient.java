package com.luis.sebolivros.infra.cep.client;

import com.luis.sebolivros.infra.cep.dto.EnderecoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class CepClient {

    private final WebClient webClient;

    public CepClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://viacep.com.br/ws").build();
    }

    public Mono<EnderecoDTO> viaCep(String cep){
        return this.webClient.get()
                .uri("/{cep}/json/", cep)
                .retrieve()
                .bodyToMono(EnderecoDTO.class);
    }
}
