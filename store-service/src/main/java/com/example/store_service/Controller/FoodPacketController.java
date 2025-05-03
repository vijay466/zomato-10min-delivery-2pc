package com.example.store_service.Controller;

import com.example.store_service.Service.FoodPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodpacket")
public class FoodPacketController {

    @Autowired
    private FoodPacketService foodPacketService;

    // Getter and Setter for foodPacketService
    public FoodPacketService getFoodPacketService() {
        return foodPacketService;
    }

    public void setFoodPacketService(FoodPacketService foodPacketService) {
        this.foodPacketService = foodPacketService;
    }

    @PostMapping("/reserve")
    public String reservePacket(@RequestParam String foodName) {
        boolean success = foodPacketService.reservePacket(foodName);
        if (success) {
            return "Packet reserved successfully.";
        } else {
            return "No food packet available to reserve.";
        }
    }

    @PostMapping("/book")
    public String bookPacket(@RequestParam String foodName) {
        boolean success = foodPacketService.bookPacket(foodName);
        if (success) {
            return "Packet booked successfully.";
        } else {
            return "No reserved packet available to book.";
        }
    }
}
