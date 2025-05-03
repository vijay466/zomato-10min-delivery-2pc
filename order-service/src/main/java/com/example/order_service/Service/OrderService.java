package com.example.order_service.Service;


import com.example.order_service.FeignClients.DeliveryServiceClient;
import com.example.order_service.FeignClients.StoreServiceClient;
import com.example.order_service.Model.OrderEntity;
import com.example.order_service.Respository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private DeliveryServiceClient deliveryServiceClient;

    @Autowired
    private StoreServiceClient storeServiceClient;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public String placeOrder(String foodName) {
        try {
            // Phase 1: Reserve
            String reserveAgentResp = deliveryServiceClient.reserveAgent();
            String reserveFoodResp = storeServiceClient.reservePacket(foodName);

//            System.out.println("ERROR MESSAGE IN reserve agent : " + reserveAgentResp);
//            System.out.println("ERROR MESSAGE IN reserve food : " + reserveFoodResp);

            if (!reserveAgentResp.contains("successfully")) {
                throw new RuntimeException(reserveAgentResp);
            }
            if(!reserveFoodResp.contains("successfully")) {
                throw new RuntimeException(reserveFoodResp);
            }


            // Phase 2: Book
            String bookAgentResp = deliveryServiceClient.bookAgent();
            String bookFoodResp = storeServiceClient.bookPacket(foodName);

//            if (!bookAgentResp.contains("successfully") || !bookFoodResp.contains("successfully")) {
//                throw new RuntimeException("Booking phase failed");
//            }
            if(!bookFoodResp.contains("successfully")) {
                throw new RuntimeException(bookFoodResp);
            }
            if (!bookAgentResp.contains("successfully")) {
                throw new RuntimeException(bookAgentResp);
            }


            // Save Order
            OrderEntity order = new OrderEntity();
            order.setFoodName(foodName);
            order.setIsPlaced(true);
            orderRepository.save(order);

            return "Order Placed Successfully";

        } catch (Exception e) {
            return "Order Failed: " + e.getMessage();
        }
    }
}

