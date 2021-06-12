package com.hidarisoft.producer.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

    private String id;
    private String model;
    private String color;
}
