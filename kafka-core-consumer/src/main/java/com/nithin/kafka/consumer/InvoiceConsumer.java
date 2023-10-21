package com.nithin.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nithin.kafka.entity.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InvoiceConsumer {

    public static final Logger LOGGER= LoggerFactory.getLogger(InvoiceConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "t-invoice",concurrency = "2",containerFactory = "invoiceDltContainerFactory")
    public void consume(String message) throws JsonProcessingException {
        var invoice=objectMapper.readValue(message, Invoice.class);
        if(invoice.getAmount()<1){
            throw new IllegalArgumentException("Invalid amount for "+invoice);
        }
        LOGGER.info("Processing invoice --> {}",invoice);
    }
}
