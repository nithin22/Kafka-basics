package com.nithin.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class GeneralLedgerConsumer {

    public static final Logger LOGGER= LoggerFactory.getLogger(GeneralLedgerConsumer.class);

    @KafkaListener(id = "general-ledger.one",topics = "t-general-ledger")
    public void consume(String message){
        LOGGER.info("From Consumer One: {}",message);
    }


    @KafkaListener(topics = "t-general-ledger")
    public void consume2(String message){
        LOGGER.info("From Consumer One: {}",message);
    }
}
