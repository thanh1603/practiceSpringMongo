package com.example.practiceSpring.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductByRequest {
    private String producId;
    private Integer amount;
    private Integer amount1;

}
