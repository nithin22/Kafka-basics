package com.nithin.kafka.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nithin.kafka.entity.CarLocation;
import com.nithin.kafka.producer.CarLocationProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class CarLocationScheduler {

    private static final Logger LOGGER= LoggerFactory.getLogger(CarLocationScheduler.class);


    private CarLocation carone;

    private CarLocation carTwo;

    private CarLocation carThree;

    @Autowired
    private CarLocationProducer carLocationProducer;

    public CarLocationScheduler(){
        var now =System.currentTimeMillis();
        carone= new CarLocation("car-one",now,0);
        carTwo= new CarLocation("car-two",now,110);
        carThree= new CarLocation("car-three",now,95);
    }

//    @Scheduled(fixedRate = 10000)
    public void generateCarLocation() throws JsonProcessingException {
        var now =System.currentTimeMillis();
        carone.setTimeStamp(now);
        carTwo.setTimeStamp(now);
        carThree.setTimeStamp(now);
        carone.setDistance(carone.getDistance()+1);
        carTwo.setDistance(carTwo.getDistance()-1);
        carThree.setDistance(carThree.getDistance()+1);
        carLocationProducer.send(carone);
        carLocationProducer.send(carTwo);
        carLocationProducer.send(carThree);
        LOGGER.info("Sent Mesages are-->{}",carone);
        LOGGER.info("Sent Mesages are-->{}",carTwo);
        LOGGER.info("Sent Mesages are-->{}",carThree);

    }
}
