
# Zomato 10-Minute Delivery – Distributed Transaction System

## 🚀 Project Goal

Build a system that guarantees **food + delivery agent availability** before confirming a 10-minute order. If either is unavailable, the order fails. This simulates a **distributed transaction** problem and solves it using **2-Phase Commit (2PC)**.

---

## 🧩 Services Involved

* **Order Service** (Coordinator)
* **Store Service** (Manages food packets)
* **Delivery Service** (Manages delivery agents)

Each service runs independently with its own MySQL database.

---

## 🕹️ Core Problem

Ensure both **food** and **agent** are available, without race conditions or double bookings, in a **distributed microservice** setup.

Requires:

* Resource locking
* Concurrency handling
* Timeout cleanup
* Atomic commitment

---

## ⚙️ Flow: 2-Phase Commit (2PC)

### 1. Phase 1: Reservation (Prepare)

1. **OrderService** receives `placeOrder(foodId)`.
2. Calls:

   * `POST /agent/reserve` on DeliveryService
   * `POST /foodpacket/reserve?foodName={}` on StoreService
3. Each service:

   * Acquires DB-level lock (pessimistic)
   * Sets `reserved = true` and `reservedAt = now()`

*If either fails → abort and rollback.*

### 2. Phase 2: Commit

If both reservations succeed:

* OrderService calls:

  * `POST /agent/book` (sets `serving = true`)
  * `POST /foodpacket/book?foodName={}` (sets `assigned = true`)
* Saves final **Order** record

---

## 🧼 Timeout & Cleanup

If commit never happens (service crash or network issue), reserved resources must be freed:

* **DeliveryCleanupService**: runs every minute and releases agents reserved > 60s
* **StoreCleanupService**: runs every minute and releases packets reserved > 60s

```java
@Scheduled(fixedRate = 60000)
public void releaseStaleReservations() { ... }
```

---

## ⚡ Concurrent Order Simulation

* Simulates **10 concurrent orders** via thread pool
* Available: 10 agents, 15 packets
* Orders 1–10 succeed, 11th fails (“No agent available”)
* Verifies no double assignment or overbooking

---

## 🧱 Tech Stack

* Spring Boot
* MySQL (one schema per service)
* Spring Data JPA (pessimistic locking)
* REST APIs & Feign clients
* Maven

---

## 📦 Database Schemas

**Agent Table**

```sql
CREATE TABLE agent (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  reserved BOOLEAN,
  serving BOOLEAN,
  reserved_at DATETIME
);
```

**FoodPacket Table**

```sql
CREATE TABLE packet (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  food_name VARCHAR(100),
  reserved BOOLEAN,
  assigned BOOLEAN,
  reserved_at DATETIME
);
```

---

## 🎯 Why 2PC?

* Guarantees **atomic** commit across services
* Prevents **partial state** changes
* Mimics real-world transaction managers without extra tooling

---

## 💡 Takeaways

* Solved **distributed resource contention**
* Implemented 2PC in microservices
* Added resilience via **cleanup tasks**
* Demonstrated concurrency handling end-to-end

---

