package com.example.store_service.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "packet")
public class FoodPacket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long foodId;  // Foreign Key to food item

    @Column(name = "food_name")  // Add this field to match the query
    private String foodName; // This is the missing foodName field

    @Column(name = "is_reserved")
    private boolean reserved;

    @Column(name = "is_assigned")
    private boolean assigned;

    @Column(name = "reserved_at")
    private LocalDateTime reservedAt;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }




    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public void setReservedAt(LocalDateTime reservedAt) {
        this.reservedAt = reservedAt;
    }
}
