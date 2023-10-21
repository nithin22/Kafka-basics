package com.nithin.kafka.producer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nithin.kafka.entity.PurchaseRequest;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PurchaseRequestProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void send(PurchaseRequest purchaseRequest) throws JsonProcessingException {
        var json=objectMapper.writeValueAsString(purchaseRequest);
        kafkaTemplate.send("t-purchase",purchaseRequest.getPrNumber(),json);
    }
}
