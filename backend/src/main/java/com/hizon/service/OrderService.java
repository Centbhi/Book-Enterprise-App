package com.hizon.service;

import java.util.List;
import com.hizon.entity.OrderStatus;
import com.hizon.model.OrderDTO;

public interface OrderService extends GenericService<OrderDTO>{
    List<OrderDTO> findByUser(Integer userId);
    List<OrderDTO> findByStatus(OrderStatus status);
    List<OrderStatus> getStatusEnum();
}
