package com.nithin.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nithin.kafka.entity.CarLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.PrimitiveIterator;

@Service
public class CarLocationProducer {


    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;



    @Autowired
    private ObjectMapper objectMapper;

    public void send(CarLocation carLocation) throws JsonProcessingException {
        var json=objectMapper.writeValueAsString(carLocation);
        kafkaTemplate.send("t-location",json);
    }
}
