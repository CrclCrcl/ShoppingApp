package com.example.ordersevice.Controller;

import com.example.ordersevice.Dto.OrderRequst;
import com.example.ordersevice.Service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "catalog",fallbackMethod = "fallback")
    @TimeLimiter(name = "catalog")
    @Retry(name = "catalog")
        public CompletableFuture<String> callOrder(@RequestBody OrderRequst orderRequst){
        return CompletableFuture.supplyAsync(()->orderService.createOrder(orderRequst));


    }

    public CompletableFuture<String> fallback(OrderRequst orderRequst,RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(() ->"Something went Wrong !");
    }
}
