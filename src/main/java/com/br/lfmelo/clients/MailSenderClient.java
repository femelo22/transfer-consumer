package com.br.lfmelo.clients;

import com.br.lfmelo.entities.dtos.EmailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "send-mail", url = "http://localhost:8083")
public interface MailSenderClient {
    @RequestMapping(method = RequestMethod.POST, value = "/send-notification", consumes = "application/json")
    void sendMailNotification(EmailDTO dto);
}
