package com.nithin.kafka.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nithin.kafka.entity.Commodity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

//@Service
public class CommodityDashBoardConsumer {

    private static final Logger LOGGER= LoggerFactory.getLogger(CommodityDashBoardConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "t-commodity",groupId = "cg-dashboard")
    public void consume(String message) throws JsonProcessingException, InterruptedException {
        var commodity=objectMapper.readValue(message, Commodity.class);
        TimeUnit.MILLISECONDS.sleep(2000);
        LOGGER.info("DashBorad logic for :{}",message);
    }
}
