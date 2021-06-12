package com.hidarisoft.producer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hidarisoft.producer.dto.CarDTO;
import com.hidarisoft.producer.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarConsumer {

    @Autowired
    private CarService carService;

    @KafkaListener(topics = "${cloudkarafka.topic}", groupId = "${spring.kafka.group-id}")
    public void receive(String payload, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) throws JsonProcessingException {
        System.out.println("Consuming payload:" + payload);
        carService.processing(payload);

    }
}
