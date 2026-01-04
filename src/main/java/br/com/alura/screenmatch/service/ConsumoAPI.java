package br.com.alura.screenmatch.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    public String obterDados(String endereco) {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .GET()
                .build();

        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException(
                        "Erro HTTP: " + response.statusCode()
                );
            }

            if (response.body() == null || response.body().isBlank()) {
                throw new RuntimeException("Resposta da API vazia");
            }

            return response.body();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao consumir API", e);
        }
    }
}