package com.br.lfmelo.consumers;

import com.br.lfmelo.entities.dtos.TranferenciaDTO;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class TransferDLQConsumer {
    @SqsListener("transfer-dlq")
    public void listenDLQ(TranferenciaDTO message) {
        System.out.println("Message received: " + message.getValue());
        System.out.println("Message received: " + message.getPayer());
        System.out.println("Message received: " + message.getPayee());
    }
}
