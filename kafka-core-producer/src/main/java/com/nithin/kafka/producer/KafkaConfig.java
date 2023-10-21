package com.nithin.kafka.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {


    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public ProducerFactory<String, String> producerFactory(){
        var properties=kafkaProperties.buildProducerProperties();

        properties.put(ProducerConfig.METADATA_MAX_AGE_CONFIG,"180000");
        return new DefaultKafkaProducerFactory<String,String>(properties);
    }
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(){
        return new KafkaTemplate<String,String>(producerFactory());
    }





    @Bean
    public NewTopic createTopic(){
        return TopicBuilder.name("t-hello").replicas(1).build();
    }

    @Bean
    public NewTopic createFixedRateTopic(){
        return TopicBuilder.name("t-fixedrate").replicas(1).build();
    }

    @Bean
    public NewTopic createMultiTopics(){
        return TopicBuilder.name("t-multi").replicas(1)
                .partitions(3).build();
    }

    @Bean
    public NewTopic createEmployeeTopics(){
        return TopicBuilder.name("t-employee").replicas(1)
                .partitions(1).build();
    }

    @Bean
    public NewTopic createComodityTopics(){
        return TopicBuilder.name("t-commodity").replicas(1)
                .partitions(1).build();
    }
    @Bean
    public NewTopic createPurchaseTopics(){
        return TopicBuilder.name("t-purchase").replicas(1)
                .partitions(1).build();
    }

    @Bean
    public NewTopic createImageTopic(){
        return TopicBuilder.name("t-image").replicas(1)
                .partitions(2).build();
    }

    @Bean
    public NewTopic createinvoiceTopic(){
        return TopicBuilder.name("t-invoice").replicas(1)
                .partitions(2).build();
    }

    @Bean
    public NewTopic deadLetterTopic(){
        return TopicBuilder.name("t-invoice-dead").replicas(1)
                .partitions(2).build();
    }
    @Bean
    public NewTopic image2(){
        return TopicBuilder.name("t-image2").replicas(1)
                .partitions(2).build();
    }
}
