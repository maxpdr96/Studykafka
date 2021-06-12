package com.hidarisoft.producer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hidarisoft.producer.dto.CarDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarConsumer {

    @KafkaListener(topics = "${cloudkarafka.topic}", groupId = "${spring.kafka.group-id}")
    public void receive(String payload, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        CarDTO carDTO = objectMapper.readValue(payload, CarDTO.class);

        System.out.println("This is a UUID: " + carDTO.getId());
        System.out.println("This is a model: " + carDTO.getModel());
        System.out.println("This is a color: " + carDTO.getColor());
        System.out.println("Using this partition: " + partitions.toString());
        System.out.println("Using this topics: " + topics.toString());
        System.out.println("Using this offsets: " + offsets.toString() + "\n");

    }
}
