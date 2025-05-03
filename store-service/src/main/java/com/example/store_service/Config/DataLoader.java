package com.example.store_service.Config;

import com.example.store_service.Model.FoodPacket;
import com.example.store_service.Repository.PacketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PacketRepository foodPacketRepository;

    // Getter and Setter for foodPacketRepository
    public PacketRepository getFoodPacketRepository() {
        return foodPacketRepository;
    }

    public void setFoodPacketRepository(PacketRepository foodPacketRepository) {
        this.foodPacketRepository = foodPacketRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 15; i++) {
            FoodPacket packet = new FoodPacket();
            packet.setFoodName("burger");
            packet.setReserved(false);
            packet.setAssigned(false);
            foodPacketRepository.save(packet);
        }
    }
}
