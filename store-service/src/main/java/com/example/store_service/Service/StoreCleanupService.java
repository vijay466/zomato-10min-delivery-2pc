package com.example.store_service.Service;


import com.example.store_service.Model.FoodPacket;
import com.example.store_service.Repository.PacketRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoreCleanupService {

    private final PacketRepository foodPacketRepository;

    public StoreCleanupService(PacketRepository foodPacketRepository) {
        this.foodPacketRepository = foodPacketRepository;
    }

    @Scheduled(fixedDelay = 30000) // Runs every 30 seconds
    @Transactional
    public void releaseStaleReservations() {
        LocalDateTime timeoutThreshold = LocalDateTime.now().minusSeconds(30);
        List<FoodPacket> stalePackets = foodPacketRepository
                .findByReservedTrueAndAssignedFalseAndReservedAtBefore(timeoutThreshold);

        for (FoodPacket packet : stalePackets) {
            packet.setReserved(false);
            packet.setReservedAt(null);
            foodPacketRepository.save(packet);
            System.out.println("Released stale reserved packet: " + packet.getId());
        }
    }
}
