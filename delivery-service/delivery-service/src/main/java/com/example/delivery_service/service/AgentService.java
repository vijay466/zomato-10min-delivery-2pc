package com.example.delivery_service.service;

import com.example.delivery_service.model.Agent;
import com.example.delivery_service.Respository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Transactional
    public boolean reserveAgent() {
        return agentRepository.findFirstByReservedFalseAndServingFalse().map(agent -> {
            agent.setReserved(true);
            agent.setReservedAt(LocalDateTime.now());  // Set the timestamp
            agentRepository.save(agent);
            return true;
        }).orElse(false);
    }


    @Transactional
    public boolean bookAgent() {
        return agentRepository.findFirstByReservedTrueAndServingFalse().map(agent -> {
            agent.setReserved(false);
            agent.setServing(true);
            agentRepository.save(agent);
            return true;
        }).orElse(false);
    }
}
