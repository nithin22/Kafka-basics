package com.nithin.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class RebalanceConsumer {

    public static final Logger LOGGER= LoggerFactory.getLogger(RebalanceConsumer.class);

    @KafkaListener(topics = "t-rebalance",concurrency = "3")
    public void consume(ConsumerRecord<String, String> consumerRecord){
        LOGGER.info("Partition: {}, Offset: {},message: {}",consumerRecord.partition(),consumerRecord.offset(),consumerRecord.value());
    }
}
