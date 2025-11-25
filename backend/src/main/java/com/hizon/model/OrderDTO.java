package com.hizon.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class OrderDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    private Integer userId;
    private List<OrderItem> orders;
    private String status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double totalCost;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime orderDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime fulfilledDate;
}
