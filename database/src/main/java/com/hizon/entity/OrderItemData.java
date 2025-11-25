package com.hizon.entity;
import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "Order_item")
public class OrderItemData{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderData order;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookData book;

    private Integer quantity;

    public OrderItemData(OrderData order, BookData book, Integer quantity) {
        this.order = order;
        this.book = book;
        this.quantity = quantity;
    }
    public OrderItemData(){}

}
