package com.hizon.service;

import java.util.List;
import com.hizon.entity.OrderStatus;
import com.hizon.model.Order;

public interface OrderService extends GenericService<Order>{
    List<Order> findByUser(Integer userId);
    List<Order> findByStatus(OrderStatus status);
    List<OrderStatus> getStatusEnum();
}
