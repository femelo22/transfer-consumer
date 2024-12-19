package com.br.lfmelo.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.lfmelo.entities.dtos.LogDTO;

@FeignClient(name = "log-service", url = "http://localhost:8000/api/v1/logstream")
public interface LogClient {

  @RequestMapping(method = RequestMethod.POST, value = "/simple-picpay-transfer")
  void ingestLog(LogDTO log);
  
}
