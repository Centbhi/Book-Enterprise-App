package com.hizon.repository;
import com.hizon.entity.OrderData;
import com.hizon.entity.OrderStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderData,Integer> {
    List<OrderData> findByUser_Id(Integer userId);
    List<OrderData> findByStatus(OrderStatus status);
}
