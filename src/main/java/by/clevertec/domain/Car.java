package by.clevertec.domain;


import by.clevertec.common.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private UUID uuid;
    private Body body;
    private String model;
    private LocalDateTime produced;


}
