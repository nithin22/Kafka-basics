package com.nithin.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.nithin.kafka.config.PaymentRequestCacheKey;
import com.nithin.kafka.entity.PaymentRequest;
import com.nithin.kafka.entity.PurchaseRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
public class PaymentRequestConsumer {

    public static final Logger LOGGER= LoggerFactory.getLogger(PaymentRequestConsumer.class);


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier("cachePaymentRequest")
    private Cache<PaymentRequestCacheKey,Boolean> cache;

    private boolean isExistingInCache(PaymentRequestCacheKey key){
        return Optional.ofNullable(cache.getIfPresent(key)).orElse(false);
    }

    @KafkaListener(topics = "t-payments")
    public void consume(String message) throws JsonProcessingException {
        var paymentRequest=objectMapper.readValue(message, PaymentRequest.class);
        var cacheKey= new PaymentRequestCacheKey(paymentRequest.getPaymentNumber(),paymentRequest.getAmount()
        ,paymentRequest.getTransactionType());
        var processed=isExistingInCache(cacheKey);
        if(processed){
            return;
        }
        LOGGER.info("Processing {}",paymentRequest);
        cache.put(cacheKey,true);
    }


}
