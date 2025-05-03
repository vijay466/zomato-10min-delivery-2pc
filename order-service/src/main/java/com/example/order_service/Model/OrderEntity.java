package com.example.order_service.Model;


import jakarta.persistence.*;


@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String foodName;
    private boolean isPlaced;


    public void setIsPlaced(boolean b) {

    }

    public void setFoodName(String foodName) {

    }
}
