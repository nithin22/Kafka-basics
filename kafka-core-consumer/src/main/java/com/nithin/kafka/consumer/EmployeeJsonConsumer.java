package com.nithin.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nithin.kafka.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class EmployeeJsonConsumer {

    public static final Logger LOGGER= LoggerFactory.getLogger(EmployeeJsonConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "t-employee2")
    public void consume(String message) throws JsonProcessingException {
        var employee=objectMapper.readValue(message, Employee.class);
        LOGGER.info("Employee is: {}",employee);
    }
}
