package com.br.lfmelo.services.impl;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lfmelo.entities.Usuario;
import com.br.lfmelo.entities.dtos.EmailDTO;
import com.br.lfmelo.services.SendNotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SendNotificationServiceImpl implements SendNotificationService {

  @Autowired
  private SendNotificationService sendNotificationService;

  @Override
  public void sendMailNotification(EmailDTO emailMessage) {

    try {
      // Criar o cliente HTTP
      HttpClient client = HttpClient.newHttpClient();
      // Criar o corpo da requisição
      ObjectMapper objectMapper = new ObjectMapper();
      String jsonBody = objectMapper.writeValueAsString(emailMessage);

      // Construir a requisição POST
      HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create("http://localhost:8083/send-notification")) // URL do endpoint
      .header("Content-Type", "application/json") // Define o cabeçalho
      .POST(HttpRequest.BodyPublishers.ofString(jsonBody)) // Define o corpo da requisição
      .build();

      // Fazer a requisição de forma assíncrona
      CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

      // Processar o resultado da requisição
      responseFuture.thenApply(HttpResponse::body) // Extrai o corpo da resposta
                    .thenAccept(body -> System.out.println("Resposta recebida: " + body)) // Exibe o corpo
                    .exceptionally(ex -> { // Lida com erros
                        System.err.println("Erro na requisição: " + ex.getMessage());
                        return null;
      });

      // Manter a aplicação ativa até que a requisição termine
      responseFuture.join(); // Aguarda o CompletableFuture terminar
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    
    throw new UnsupportedOperationException("Unimplemented method 'sendMailNotification'");
  }

  public EmailDTO createEmail(BigDecimal valor, Usuario usuario) {
    return new EmailDTO("noreply@gmail.com", "Transferência concluida!", "Sua transferência de " + valor + " foi concluida com sucesso!");
  }

}
