package com.hizon.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class Order {
    private Integer id;
    private Integer userId;
    private Map<Book, Integer> books; //value = quantity
    private Double totalCost;
    private String status;
    private Boolean isFulfilled;
    private LocalDateTime orderDate;
    private LocalDateTime fulfilledDate;
}
