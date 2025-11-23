package com.hizon.entity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
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

    @ElementCollection
    @CollectionTable(
        name = "order_books",
        joinColumns = @JoinColumn(name = "order_id")
    )
    @MapKeyJoinColumn(name = "book.id")
    @Column(name = "quantity")
    private Map<BookData,Integer> books = new HashMap<>();
    
    private double totalCost;
    private String status;
    private Boolean isFulfilled = false;

    @Column(name = "order_date", nullable = false, updatable = false)
    private LocalDateTime orderDate = LocalDateTime.now();

    @Column(name = "fulfilled_date")
    private LocalDateTime orderFulfilledDate;
}
