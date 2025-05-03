package com.example.store_service.Service;

import com.example.store_service.Repository.PacketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

@Service
public class FoodPacketService {

    @Autowired
    private PacketRepository foodPacketRepository;

    @Transactional
    public boolean reservePacket(String foodName) {
        // Correct method call matching the repository
        return foodPacketRepository
                .findFirstByFoodNameAndReservedFalseAndAssignedFalse(foodName)
                .map(packet -> {
                    packet.setReserved(true);
                    packet.setReservedAt(LocalDateTime.now());
                    foodPacketRepository.save(packet);
                    return true;
                })
                .orElse(false); // Return false if no packet is found
    }

    @Transactional
    public boolean bookPacket(String foodName) {
        // Correct method call matching the repository
        return foodPacketRepository
                . findFirstByFoodNameAndReservedTrueAndAssignedFalse(foodName)
                .map(packet -> {
                    packet.setReserved(false);
                    packet.setAssigned(true);
                    foodPacketRepository.save(packet);
                    return true;
                })
                .orElse(false); // Return false if no packet is found
    }
}
