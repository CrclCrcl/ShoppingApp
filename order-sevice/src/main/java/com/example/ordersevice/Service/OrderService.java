package com.example.ordersevice.Service;

import com.example.ordersevice.Dto.CatalogResponse;
import com.example.ordersevice.Dto.OrderRequst;
import com.example.ordersevice.Dto.OrderedItemsDto;
import com.example.ordersevice.Entity.Order;
import com.example.ordersevice.Entity.OrderedItems;
import com.example.ordersevice.Event.OrderEvent;
import com.example.ordersevice.Repository.OrderRepository;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;
    private final Tracer tracer;
    private final KafkaTemplate<String,OrderEvent> kafkaTemplate;
    public String createOrder(OrderRequst orderRequst){
        Order order = new Order();
        order.setNumbofOrder(UUID.randomUUID().toString());

       List<OrderedItems> orderedItems = orderRequst.getOrderedItemsDtos()
                .stream()
                .map(this::mapToDto)
                .toList();
        log.info("Waiting for Catalog Service");

       Span catalogService = tracer.nextSpan().name("CatalogService");

       try(Tracer.SpanInScope spanInScope = tracer.withSpan(catalogService.start())){

       }finally {
           catalogService.end();
       }

        List<String> uniCodes = order.getOrderedItems().stream()
                .map(OrderedItems::getUniCode)
                .toList();

        order.setOrderedItems(orderedItems);
        CatalogResponse[] catalogResponses = webClient.get()
                .uri("http://localhost:8282/catalog",
                        uriBuilder -> uriBuilder.queryParam("UniCode",uniCodes).build())
                .retrieve()
                .bodyToMono(CatalogResponse[].class)
                .block();

        boolean allProdInCatalog = Arrays.stream(catalogResponses).allMatch(CatalogResponse::isAvailable);

        if(allProdInCatalog){
            orderRepository.save(order);
            kafkaTemplate.send("shopping-app", new OrderEvent(order.getNumbofOrder()));
            return "Order Placed Succesfully!";
        }else{
            throw new IllegalArgumentException("Item is temporarily unavailable, please try again later.");
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
