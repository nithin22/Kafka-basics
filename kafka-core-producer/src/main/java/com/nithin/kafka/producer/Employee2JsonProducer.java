package com.nithin.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nithin.kafka.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Employee2JsonProducer {


    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    public ObjectMapper objectMapper;

    public void sendMessage(Employee employee) throws JsonProcessingException {
        var json=objectMapper.writeValueAsString(employee);
        kafkaTemplate.send("t-employee2",json);
    }
}
