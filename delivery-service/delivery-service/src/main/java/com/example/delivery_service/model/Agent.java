package com.example.delivery_service.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean reserved;   // for reservation
    private boolean serving;// serving an order
    @Column(name = "reserved_at")
    private LocalDateTime reservedAt;


    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public void setServing(boolean serving) {
        this.serving = serving;
    }

    public LocalDateTime getReservedAt() { return reservedAt; }
    public void setReservedAt(LocalDateTime reservedAt) { this.reservedAt = reservedAt; }

}


