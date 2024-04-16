package com.example.ordersevice.Service;

import com.example.ordersevice.Dto.OrderRequst;
import com.example.ordersevice.Dto.OrderedItemsDto;
import com.example.ordersevice.Entity.Order;
import com.example.ordersevice.Entity.OrderedItems;
import com.example.ordersevice.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    public void createOrder(OrderRequst orderRequst){
        Order order = new Order();
        order.setNumbofOrder(UUID.randomUUID().toString());

       List<OrderedItems> orderedItems = orderRequst.getOrderedItemsDtos()
                .stream()
                .map(this::mapToDto)
                .toList();



        order.setOrderedItems(orderedItems);
        orderRepository.save(order);
    }

    private OrderedItems mapToDto(OrderedItemsDto orderedItemsDto) {
     OrderedItems orderedItems = new OrderedItems();
     orderedItems.setPrice(orderedItems.getPrice());
     orderedItems.setQuantity(orderedItems.getQuantity());
     orderedItems.setUniCode(orderedItems.getUniCode());
    return orderedItems;
    }
}