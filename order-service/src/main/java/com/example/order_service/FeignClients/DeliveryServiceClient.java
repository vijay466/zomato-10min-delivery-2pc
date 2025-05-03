package com.example.order_service.FeignClients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "delivery-service", url = "http://localhost:8082")
public interface DeliveryServiceClient {

    @PostMapping("/agent/reserve")
    String reserveAgent();

    @PostMapping("/agent/book")
    String bookAgent();
}
