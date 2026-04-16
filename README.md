# 🚀 Distributed Job Scheduler  
### Fault-Tolerant Scalable Asynchronous Processing System

A distributed system designed to process asynchronous jobs reliably using **Kafka, Redis, and Java**, supporting **retry mechanisms, fault tolerance, and horizontal scalability**.

---

## 📌 Overview

This project implements a **distributed job processing system** where tasks are produced, queued, and processed asynchronously by worker services. It ensures **high reliability and fault tolerance** using retry strategies and dead-letter queues.

---

## 🏗️ Architecture
```text
Producer → Kafka Topic (jobs) → Worker Consumers → Redis (Job State)
↓
Retry Mechanism
↓
Dead Letter Queue (DLQ)
```


---

## 🛠️ Tech Stack

- **Java**
- **Apache Kafka** (Message Queue)
- **Redis** (Caching & State Management)
- **Maven**

---

## ✨ Key Features

✔️ Asynchronous job processing using Kafka  
✔️ Fault-tolerant architecture with retry mechanism  
✔️ Dead Letter Queue (DLQ) for failed jobs  
✔️ Redis-based job state tracking  
✔️ Horizontal scalability with stateless workers  
✔️ Handles high-volume concurrent processing  

---

## 🔁 Workflow

1. **Producer** sends jobs to Kafka topic  
2. **Workers** consume jobs asynchronously  
3. Jobs are processed and:
   - ✅ Marked **COMPLETED** in Redis  
   - ❌ Retried on failure  
4. After max retries → moved to **DLQ**  
5. Redis maintains job status (retry count, completion)

---

## 📂 Project Structure
```text
distributed-job-scheduler/
│── src/
│ ├── main/java/com/scheduler/
│ │ ├── ProducerApp.java
│ │ ├── Worker.java
│ │ ├── Config.java
│ ├── resources/
│ │ └── config.properties
│── pom.xml
```

---

## 🔥 Core Concepts Implemented

- Distributed Systems Design  
- Message Queueing (Kafka)  
- Fault Tolerance  
- Retry with Backoff Strategy  
- Dead Letter Queue (DLQ)  
- Stateless Microservices  
- Caching using Redis  

---

## ▶️ How to Run

### Prerequisites

- Java 8+
- Maven
- Kafka running locally (`localhost:9092`)
- Redis running locally (`localhost:6379`)

---

### Step 1: Start Kafka & Redis

```bash
# Start Kafka
zookeeper-server-start.sh config/zookeeper.properties
kafka-server-start.sh config/server.properties

# Start Redis
redis-server
```

### Step 2: Run Producer
```text
mvn compile
java com.scheduler.ProducerApp
```
### Step 3: Run Worker

```text
java com.scheduler.Worker
```
---
### 🧪 Sample Output
```text
Processing job-1
Retrying job-1
Processing job-1
Moved to DLQ: job-1
```
---
### 💡 Design Highlights
- Stateless workers → easy horizontal scaling
- Kafka-based decoupling → high throughput
- Redis for fast state tracking
- DLQ ensures no data loss
- Retry logic improves reliability
---
### 🚀 Future Enhancements
- Exponential backoff strategy
- Monitoring & logging (Prometheus + Grafana)
- Docker containerization
- CI/CD pipeline (GitHub Actions)
- REST API for job submission
- Distributed tracing

---
### 👩‍💻 Author
Swathi Mittapalli

Aspiring Software Engineer
Strong in DSA (750+ problems solved)
Interested in Distributed Systems & Backend Engineering
⭐ If you like this project

Give it a ⭐ on GitHub — it helps and motivates me to build more!
