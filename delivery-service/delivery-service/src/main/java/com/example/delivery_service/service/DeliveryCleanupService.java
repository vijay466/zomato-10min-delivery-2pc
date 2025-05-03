package com.example.delivery_service.service;

import com.example.delivery_service.Respository.AgentRepository;
import com.example.delivery_service.model.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryCleanupService {

    @Autowired
    private AgentRepository deliveryAgentRepository;

    @Scheduled(fixedRate = 30000) // every 30 seconds
    public void releaseStaleReservations() {
        LocalDateTime timeout = LocalDateTime.now().minusSeconds(30);

        List<Agent> staleAgents =
                deliveryAgentRepository.findByReservedTrueAndServingFalseAndReservedAtBefore(timeout);

        for (Agent agent : staleAgents) {
            agent.setReserved(false);
            agent.setReservedAt(null);
            deliveryAgentRepository.save(agent);
        }
    }
}
