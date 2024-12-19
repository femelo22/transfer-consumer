package com.br.lfmelo.utils;

import java.time.LocalDateTime;
import java.util.UUID;

import com.br.lfmelo.entities.dtos.LogDTO;

public class LogUtil {
  
  public static LogDTO createLog(String mensagem, String acao, String erro) {
    int status = erro != null ? 500 : 200;

    return new LogDTO(
      UUID.randomUUID().toString(),
      LocalDateTime.now(),
      status,
      mensagem,
      acao,
      erro);
  }
}
