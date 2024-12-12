package com.br.lfmelo.consumers;

import com.br.lfmelo.entities.dtos.TransferenciaDTO;
import com.br.lfmelo.services.TransferenciaService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferConsumer {

    @Autowired
    private TransferenciaService transferenciaService;

    @SqsListener("transfer-queue")
    public void listen(TransferenciaDTO message) {
        System.out.println("Message received: " + message.getValue());
        System.out.println("Message received: " + message.getPayer());
        System.out.println("Message received: " + message.getPayee());

        transferenciaService.efetuarTransferencia(message);
    }
}
