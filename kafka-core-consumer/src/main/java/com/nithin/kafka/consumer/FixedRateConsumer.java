package com.nithin.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class FixedRateConsumer {

    public static final Logger LOGGER= LoggerFactory.getLogger(FixedRateConsumer.class);


//    @KafkaListener(groupId = "myGoup",topics = "t-fixedrate")
    public void consumer(String message){
        LOGGER.info("Consuming the message ->{}",message);
    }
}
