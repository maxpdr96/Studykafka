package com.hidarisoft.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hidarisoft.producer.dto.CarDTO;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    ObjectMapper objectMapper = new ObjectMapper();

    public void processing(String payload) throws JsonProcessingException {
        //{"id":"123321","model":"gol","color":"red"}
        //Using objectMapper
        CarDTO carDTO = objectMapper.readValue(payload, CarDTO.class);

        //Using JsonPath
        CarDTO carDTO1 = new CarDTO();
        carDTO1.setId(JsonPath.read(payload, "$.id"));
        carDTO1.setModel(JsonPath.read(payload, "$.model"));
        carDTO1.setColor(JsonPath.read(payload, "$.color"));

        System.out.println("This is a UUID: " + carDTO.getId());
        System.out.println("This is a model: " + carDTO.getModel());
        System.out.println("This is a color: " + carDTO.getColor());

        System.out.println("This is a UUID1: " + carDTO1.getId());
        System.out.println("This is a model1: " + carDTO1.getModel());
        System.out.println("This is a color1: " + carDTO1.getColor());


    }


}
