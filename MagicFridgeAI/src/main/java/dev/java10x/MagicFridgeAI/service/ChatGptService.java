package dev.java10x.MagicFridgeAI.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpHeaders;
import java.util.Map;

@Service
public class ChatGptService {

    private final WebClient webClient;
    @Value("${api.key}")
    private String apiKey;

    public ChatGptService(WebClient webClient){
        this.webClient = webClient;
    }

    public Mono<String> generateRecipe() {

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4o",
                "input", "Me sugira uma receita simples com ingredientes comuns."
        );


        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError(),
                        response -> Mono.error(new RuntimeException("Erro na requisição: " + response.statusCode()))
                )
                .onStatus(
                        status -> status.is5xxServerError(),
                        response -> Mono.error(new RuntimeException("Erro no servidor OpenAI!"))
                )
                .bodyToMono(String.class);                                           // recebe como String por enquanto
    }
}
