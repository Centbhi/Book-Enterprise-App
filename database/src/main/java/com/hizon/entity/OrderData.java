package com.hizon.entity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "Order_data")
public class OrderData{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserData user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemData> orders = new ArrayList<>();

    private String status;
    private Double totalCost;

    @Column(name = "order_date", nullable = false, updatable = false)
    private LocalDateTime orderDate = LocalDateTime.now();

    @Column(name = "fulfilled_date")
    private LocalDateTime fulfilledDate;
}
