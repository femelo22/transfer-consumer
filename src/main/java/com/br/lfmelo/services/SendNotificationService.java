package com.br.lfmelo.services;

import com.br.lfmelo.entities.dtos.EmailDTO;

public interface SendNotificationService {
   void sendMailNotification(EmailDTO emailMessage);
}
