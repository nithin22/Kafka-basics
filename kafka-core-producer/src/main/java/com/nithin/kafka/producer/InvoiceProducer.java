package com.nithin.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nithin.kafka.entity.Invoice;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InvoiceProducer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;


    public void send(Invoice invoice) throws JsonProcessingException {
        var json=objectMapper.writeValueAsString(invoice);
        kafkaTemplate.send("t-invoice",invoice.getAmount()%2,invoice.getInvoiceNumber(),json);

    }
}
