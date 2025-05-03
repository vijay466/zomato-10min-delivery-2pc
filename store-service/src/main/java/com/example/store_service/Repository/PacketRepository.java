package com.example.store_service.Repository;

import com.example.store_service.Model.FoodPacket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PacketRepository extends JpaRepository<FoodPacket, Long> {

    // Method to find the first unreserved packet with pessimistic write lock
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<FoodPacket> findFirstByFoodNameAndReservedFalseAndAssignedFalse(@Param("foodName") String foodName);

    // Method to find the first reserved packet with pessimistic write lock
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<FoodPacket> findFirstByFoodNameAndReservedTrueAndAssignedFalse(@Param("foodName") String foodName);

    List<FoodPacket> findByReservedTrueAndAssignedFalseAndReservedAtBefore(LocalDateTime threshold);

}
