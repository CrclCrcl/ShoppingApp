package com.example.ordersevice.Controller;

import com.example.ordersevice.Dto.OrderRequst;
import com.example.ordersevice.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
        public String callOrder(@RequestBody OrderRequst orderRequst){
        orderService.createOrder(orderRequst);
        return "Order Placed Successfully";

    }
}
