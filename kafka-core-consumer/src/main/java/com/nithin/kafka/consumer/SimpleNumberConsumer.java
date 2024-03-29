package com.nithin.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nithin.kafka.entity.SimpleNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SimpleNumberConsumer {

    public static final Logger LOGGER= LoggerFactory.getLogger(SimpleNumberConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;


    @KafkaListener(topics = "t-simple-number")
    public void consume(String message) throws JsonProcessingException {
        var simpleNumber=objectMapper.readValue(message, SimpleNumber.class);

        if(simpleNumber.getNumber()%2!=0){
            throw new IllegalArgumentException("Odd number: "+simpleNumber.getNumber());
        }
        LOGGER.info("Processing the simpleNumber: {}",simpleNumber);
    }
}
