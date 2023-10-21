package com.nithin.kafka.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nithin.kafka.entity.FoodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FoodOrderConsumer {


    public static final Logger LOGGER= LoggerFactory.getLogger(FoodOrderConsumer.class);
    @Autowired
    private ObjectMapper objectMapper;

    public static final int MAX_ORDER_AMOUNT=7;


    @KafkaListener(topics = "t-food-order",errorHandler = "myFoodOrderErrorHandler")
    public void consume(String message) throws JsonProcessingException {
        var foodOrder=objectMapper.readValue(message, FoodOrder.class);
        if(foodOrder.getAmount()>MAX_ORDER_AMOUNT){
            throw new RuntimeException("Food amount is greater");
        }
        LOGGER.info("Processing food Order: {}",foodOrder);
    }







}
