package com.nithin.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.nithin.kafka.entity.PurchaseRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
public class PurchaseRequestConsumer {

    public static final Logger LOGGER= LoggerFactory.getLogger(PurchaseRequestConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier("cachePurchaseRequest")
    private Cache<Integer, Boolean> cache;


    private boolean isExistingInCache(int purchaseRequestId){
        return Optional.ofNullable(cache.getIfPresent(purchaseRequestId)).orElse(false);
    }

    @KafkaListener(topics = "t-purchase")
    public void consume(String message) throws JsonProcessingException {
        var purchaseRequest=objectMapper.readValue(message, PurchaseRequest.class);
        var processed=isExistingInCache(purchaseRequest.getId());
        if(processed){
            return;
        }
        LOGGER.info("Processing {}",purchaseRequest);
        cache.put(purchaseRequest.getId(),true);
    }

}
