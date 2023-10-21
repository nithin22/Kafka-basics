package com.nithin.kafka.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class GeneralLedgerScheduler {

    @Autowired
    private KafkaListenerEndpointRegistry registry;

    public static final Logger LOGGER= LoggerFactory.getLogger(GeneralLedgerScheduler.class);



    @Scheduled(cron = "0 0 23 * * ?")
    public void stop(){
        LOGGER.info("Stopped consumer");
        registry.getListenerContainer("general-ledger.one").pause();
    }
    @Scheduled(cron = "1 0 0 * * ?")
    public void start(){
        LOGGER.info("Stopped consumer");
        registry.getListenerContainer("general-ledger.one").resume();
    }



}
