package com.hizon.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class Order {
    private Integer id;
    private Integer userId;
    private Map<Integer, Integer> books; // key = bookId, value = quantity
    private double totalCost;
    private String status;
    private Boolean isFulfilled;
    private LocalDateTime orderDate;
    private LocalDateTime fulfilledDate;
}
