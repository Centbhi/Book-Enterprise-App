package com.hizon.serviceImpl;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.hizon.entity.OrderData;
import com.hizon.model.Order;
import com.hizon.repository.OrderRepository;
import com.hizon.service.OrderService;

@Service
public class OrderServiceImpl extends GenericServiceImpl<OrderData, Order> implements OrderService{
    private final OrderRepository repo;
    private final ModelMapper mapper;

    public OrderServiceImpl(JpaRepository<OrderData,Integer> repo, ModelMapper mapper){
        super(repo,mapper,OrderData.class,Order.class);
        this.repo = (OrderRepository) repo;
        this.mapper = mapper;
    }

}
