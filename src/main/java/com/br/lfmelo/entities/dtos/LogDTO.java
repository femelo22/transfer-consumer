package com.br.lfmelo.entities.dtos;

import java.time.LocalDateTime;

public record LogDTO(String idLog, LocalDateTime dataHora, int status, String mensagem, String acao, String erro) {
  
}
