package com.example.delivery_service.Respository;

import com.example.delivery_service.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Agent> findFirstByReservedFalseAndServingFalse();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Agent> findFirstByReservedTrueAndServingFalse();

    List<Agent> findByReservedTrueAndServingFalseAndReservedAtBefore(LocalDateTime time);
}
