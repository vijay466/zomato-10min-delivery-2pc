package com.example.delivery_service.Config;


import com.example.delivery_service.model.Agent;
import com.example.delivery_service.Respository.AgentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AgentRepository agentRepository;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Agent agent = new Agent();
            agent.setReserved(false);
            agent.setServing(false);
            agentRepository.save(agent);
        }
    }
}
