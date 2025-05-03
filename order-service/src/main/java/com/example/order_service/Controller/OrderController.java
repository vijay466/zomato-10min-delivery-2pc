package com.example.order_service.Controller;


import com.example.order_service.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public String placeOrder(@RequestParam String foodName) {
        return orderService.placeOrder(foodName);
    }

    @PostMapping("/placeMultiple")
    public void placeMultipleOrders(@RequestParam String foodName) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(orderService.placeOrder(foodName));
            }).start();
        }
    }
}
