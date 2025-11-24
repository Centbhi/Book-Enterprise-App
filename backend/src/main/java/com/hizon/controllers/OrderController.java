package com.hizon.controllers;

import com.hizon.entity.OrderStatus;
import com.hizon.model.Order;
import com.hizon.service.OrderService;

import java.util.List;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/order")
public class OrderController extends GenericController<Order>{

    private final OrderService service;
    public OrderController (OrderService service){
        super(service);
        this.service = service;
    }

    @GetMapping("/user/{userId}")
    public List<Order> getByUser(@PathVariable Integer userId) {
        return service.findByUser(userId);
    }

    @GetMapping("/status/{status}")
    public List<Order> getByStatus(@PathVariable OrderStatus status) {
        return service.findByStatus(status);
    }

    @GetMapping("/status/list")
    public List<OrderStatus> getStatusEnum(){
        return service.getStatusEnum();
    }

}