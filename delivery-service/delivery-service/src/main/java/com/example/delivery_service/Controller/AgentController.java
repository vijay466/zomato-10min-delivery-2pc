package com.example.delivery_service.Controller;
import com.example.delivery_service.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agent")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @PostMapping("/reserve")
    public String reserveAgent() {
        boolean success = agentService.reserveAgent();
        if (success) {
            return "Agent reserved successfully.";
        } else {
            return "No agent available to reserve.";
        }
    }

    @PostMapping("/book")
    public String bookAgent() {
        boolean success = agentService.bookAgent();
        if (success) {
            return "Agent booked successfully.";
        } else {
            return "No reserved agent available to book.";
        }
    }
}
