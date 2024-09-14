package by.clevertec.domain;


import by.clevertec.common.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Car {
    private UUID uuid;
    private Body body;
    private String model;
    private LocalDateTime produced;

}
