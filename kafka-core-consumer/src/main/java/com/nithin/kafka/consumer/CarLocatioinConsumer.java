package com.nithin.kafka.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nithin.kafka.entity.CarLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class CarLocatioinConsumer {

    private static final Logger LOGGER= LoggerFactory.getLogger(CarLocatioinConsumer.class);


    @Autowired
    private ObjectMapper objectMapper;


    @KafkaListener(topics = "t-location",groupId = "cg-all-locations")
    public void listenALl(String message) throws JsonProcessingException {
        var carLocation=objectMapper.readValue(message, CarLocation.class);
        LOGGER.info("listenAll: {}",carLocation);
    }

    @KafkaListener(topics = "t-location",groupId = "cg-far-location",containerFactory = "farLocationContainerFactory")
    public void listenFar(String message) throws JsonProcessingException {
        var carLocation=objectMapper.readValue(message, CarLocation.class);
//        if(carLocation.getDistance()<100)
//        {
//            return;
//        }
        LOGGER.info("listen only far : {}",carLocation);
    }


}
