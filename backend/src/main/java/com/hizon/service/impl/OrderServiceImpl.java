package com.hizon.service.impl;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.hizon.entity.OrderData;
import com.hizon.model.Order;
import com.hizon.repository.OrderRepository;
import com.hizon.service.OrderService;

@Service
public class OrderServiceImpl extends GenericServiceImpl<OrderData, Order> implements OrderService{
    private final OrderRepository repo;
    private final ModelMapper mapper;

    public OrderServiceImpl(OrderRepository repo, ModelMapper mapper){
        super(repo,mapper,OrderData.class,Order.class);
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public Order update(int id,Order model){
        OrderData entity = repo.findById(id).orElseThrow(()->
            new RuntimeException(OrderData.class.getSimpleName() + " not found with id: " + id));

        if(Boolean.TRUE.equals(model.getIsFulfilled())&&(entity.getFulfilledDate()==null)){
            entity.setFulfilledDate(LocalDateTime.now());
        }

        mapper.map(model,entity);
        OrderData saved = repo.save(entity);
        return mapper.map(saved,Order.class);
    }

}
