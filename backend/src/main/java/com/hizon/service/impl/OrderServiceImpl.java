package com.hizon.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.hizon.entity.BookData;
import com.hizon.entity.OrderData;
import com.hizon.entity.OrderStatus;
import com.hizon.model.Book;
import com.hizon.model.Order;
import com.hizon.repository.BookRepository;
import com.hizon.repository.OrderRepository;
import com.hizon.service.OrderService;

@Service
public class OrderServiceImpl extends GenericServiceImpl<OrderData, Order> implements OrderService{
    private final OrderRepository repo;
    private final ModelMapper mapper;
    private final BookRepository bookRepo;

    public OrderServiceImpl(OrderRepository repo, ModelMapper mapper, BookRepository bookRepo){
        super(repo,mapper,OrderData.class,Order.class);
        this.repo = repo;
        this.bookRepo = bookRepo;
        this.mapper = mapper;
    }

    @Override
    public Order create(Order model){
        OrderData entity = mapper.map(model, OrderData.class);
        mapBook(model, entity);
        calculateCost(entity);
        OrderData saved = repo.save(entity);
        return mapper.map(saved,Order.class);
    }

    @Override
    public Order update(int id,Order model){
        OrderData entity = repo.findById(id).orElseThrow(()->
            new RuntimeException(OrderData.class+ " not found with id: " + id));

        //Ensure Skip (setSkipNullEnabled)
        model.setFulfilledDate(null);
        model.setOrderDate(null);
        model.setTotalCost(null);

        if(Boolean.TRUE.equals(model.getIsFulfilled())&&(entity.getFulfilledDate()==null)){
            entity.setFulfilledDate(LocalDateTime.now());
        }

        mapper.map(model,entity);
        mapBook(model, entity);
        calculateCost(entity);

        OrderData saved = repo.save(entity);
        return mapper.map(saved,Order.class);
    }
    
    // BookId in model -> BookData in entity
    private void mapBook(Order model, OrderData entity){
        if(model.getBooks()==null){
            return;
        }
        Map<BookData, Integer> booksMap = new HashMap<>(); 
        for (Map.Entry<Book, Integer> entry : model.getBooks().entrySet()) {
            BookData book = bookRepo.getReferenceById(entry.getKey().getId());
            booksMap.put(book, entry.getValue());
        }
        entity.setBooks(booksMap);
    }

    private void calculateCost(OrderData entity){
        double total = 0;
        for (Map.Entry<BookData,Integer> entry: entity.getBooks().entrySet()){
            total += entry.getValue() * entry.getKey().getPrice();
        }
        entity.setTotalCost(total);
    }

    @Override
    public List<Order> findByUser(Integer userId) {
        List<OrderData> entities = repo.findByUser_Id(userId);
        List<Order> orders = new ArrayList<>();
        for (OrderData entity : entities) {
            Order model = (mapper.map(entity, Order.class));

            // convert BookData -> Book for DTO map
            Map<Book, Integer> modelBooks = new HashMap<>();
            for (Map.Entry<BookData, Integer> e : entity.getBooks().entrySet()) {
                modelBooks.put(mapper.map(e.getKey(), Book.class), e.getValue());
            }
            model.setBooks(modelBooks);
            orders.add(model);
        }
        return orders;
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        List<OrderData> entities = repo.findByStatus(status);
        List<Order> orders = new ArrayList<>();
        for (OrderData entity : entities) {
            Order model = (mapper.map(entity, Order.class));

            // convert BookData -> Book for DTO map
            Map<Book, Integer> modelBooks = new HashMap<>();
            for (Map.Entry<BookData, Integer> e : entity.getBooks().entrySet()) {
                modelBooks.put(mapper.map(e.getKey(), Book.class), e.getValue());
            }
            model.setBooks(modelBooks);
            orders.add(model);
        }
        return orders;
    }

    @Override
    public List<OrderStatus> getStatusEnum(){
        return Arrays.asList(OrderStatus.values());
    }
}
