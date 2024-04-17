package com.example.ordersevice.Service;

import com.example.ordersevice.Dto.OrderRequst;
import com.example.ordersevice.Dto.OrderedItemsDto;
import com.example.ordersevice.Entity.Order;
import com.example.ordersevice.Entity.OrderedItems;
import com.example.ordersevice.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;
    public void createOrder(OrderRequst orderRequst){
        Order order = new Order();
        order.setNumbofOrder(UUID.randomUUID().toString());

       List<OrderedItems> orderedItems = orderRequst.getOrderedItemsDtos()
                .stream()
                .map(this::mapToDto)
                .toList();



        order.setOrderedItems(orderedItems);
        Boolean result =webClient.get()
                .uri("http://localhost:8282/catalog")
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if(result){
            orderRepository.save(order);
        }else{
            throw new NotFoundProductException( "Item is temporarily unavailable, please try again later.")
        }


    }

    private OrderedItems mapToDto(OrderedItemsDto orderedItemsDto) {
     OrderedItems orderedItems = new OrderedItems();
     orderedItems.setPrice(orderedItems.getPrice());
     orderedItems.setQuantity(orderedItems.getQuantity());
     orderedItems.setUniCode(orderedItems.getUniCode());
    return orderedItems;
    }
}
