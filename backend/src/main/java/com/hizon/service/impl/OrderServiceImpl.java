package com.hizon.service.impl;

import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.hizon.entity.BookData;
import com.hizon.entity.OrderData;
import com.hizon.entity.OrderItemData;
import com.hizon.entity.OrderStatus;

import com.hizon.model.Book;
import com.hizon.model.OrderDTO;
import com.hizon.model.OrderItem;

import com.hizon.repository.BookRepository;
import com.hizon.repository.OrderRepository;
import com.hizon.repository.UserRepository;

import com.hizon.service.OrderService;


@Service
public class OrderServiceImpl extends GenericServiceImpl<OrderData, OrderDTO> implements OrderService{
    private final OrderRepository repo;
    private final ModelMapper mapper;
    private final BookRepository bookRepo;
    private final UserRepository userRepo;
    Logger logger = Logger.getLogger(getClass().getName());

    public OrderServiceImpl(OrderRepository repo, ModelMapper mapper, BookRepository bookRepo, UserRepository userRepo){
        super(repo,mapper,OrderData.class,OrderDTO.class);
        this.repo = repo;
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
        this.mapper = mapper;
    }


    @Override
    public OrderDTO create(OrderDTO model){
        OrderData dest = mapper.map(model, OrderData.class);
        OrderData saved = repo.save(mapToEntity(model,dest));
        return mapToDTO(saved);
    }

    @Override
    public OrderDTO read(Integer id){
        OrderData entity = repo.findById(id)
            .orElseThrow(() -> new RuntimeException(OrderData.class + "not found with id: " + id));
        return mapToDTO(entity);
    }

    @Override
    public OrderDTO update(int id,OrderDTO model){
        OrderData entity = repo.findById(id).orElseThrow(()->
            new RuntimeException(OrderData.class+ " not found with id: " + id));

        if("FULFILLED".equals(entity.getStatus())&&(entity.getFulfilledDate()==null)){
            entity.setFulfilledDate(LocalDateTime.now());
        }

        mapToEntity(model,entity);

        OrderData saved = repo.save(entity);

        return mapToDTO(saved);
    }

    @Override
    public List<OrderDTO> findByUser(Integer userId) {
        List<OrderData> entities = repo.findByUser_Id(userId);
        List<OrderDTO> models = new ArrayList<>();
        for (OrderData entity : entities){
            models.add(mapToDTO(entity));
        }
        return models;
    }

    @Override
    public List<OrderDTO> findByStatus(OrderStatus status) {
        List<OrderData> entities = repo.findByStatus(status);
        List<OrderDTO> models = new ArrayList<>();
        for (OrderData entity : entities){
            models.add(mapToDTO(entity));
        }
        return models;
    }

    @Override
    public List<OrderStatus> getStatusEnum(){
        return Arrays.asList(OrderStatus.values());
    }
    
    private OrderData mapToEntity(OrderDTO source, OrderData dest){
        dest.getOrders().clear();

        for (OrderItem order : source.getOrders()) {
            BookData bookData = bookRepo.getReferenceById(order.getBook().getId());
            dest.getOrders().add(new OrderItemData(dest, bookData, order.getQuantity()));
        }

        dest.setUser(userRepo.getReferenceById(source.getUserId()));
        dest.setTotalCost(calculateCost(dest));

        return dest;
    }

    private OrderDTO mapToDTO(OrderData source){
        OrderDTO model = mapper.map(source, OrderDTO.class);
        List<OrderItem> orderItem = new ArrayList<>();

        for (OrderItemData orderItemData : source.getOrders()) {
            orderItem.add(
                new OrderItem(
                    mapper.map(orderItemData.getBook(), Book.class), 
                    orderItemData.getQuantity()
                )
            );
        }
        model.setOrders(orderItem);
        return model;
    }

    private double calculateCost(OrderData entity){
        double total = 0;
        for(OrderItemData item : entity.getOrders()){
            total += (item.getBook().getPrice() * item.getQuantity());
        }
        return total;
    }
}
