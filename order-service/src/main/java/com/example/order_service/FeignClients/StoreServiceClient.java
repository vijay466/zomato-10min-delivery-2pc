package com.example.order_service.FeignClients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "store-service", url = "http://localhost:8083")
public interface StoreServiceClient {

    @PostMapping("/foodpacket/reserve")
    String reservePacket(@RequestParam String foodName);

    @PostMapping("/foodpacket/book")
    String bookPacket(@RequestParam String foodName);
}
