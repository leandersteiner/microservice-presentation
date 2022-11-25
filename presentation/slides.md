---
title: Microservices
author: Leander Steiner
date: 29.11.2022
transition: fade
theme: white
width: 1200
---

# Introduction

## Outline

- What are Microservices
- Inter-service Communication
- Modeling Microservices
- Data Management
- Service Communication

## Tools

- Git
- Containerization (Docker, Podman)
- Orchestration (Kubernetes)
- Provisioning (Terraform, Ansible)
- CI/CD (Jenkins, CircleCI, Github Actions)
- Databases (SQL, NoSQL)
- Message/Event Broker (Kafka, NATS, RabbitMQ)
- Monitoring & Tracing (Prometheus, Grafana, Zipkin)

## What are Microservices


![](img/microservice-example.svg)

---


![](img/microservices-vs-monolith.svg)

---

![](img/microservice-vs-monolith-scaling.svg)

---

![](img/monolith-example.svg)

---

![](img/monolith-example-as-microservice.svg)

---

A microservice contains everything to make one feature work.

This usually includes:

- Routing
- Middlewares
- Business Logic
- Database Access
- Subscribing to Messages/Events
- Publishing Messages/Events

# Data Management

## Database per service

- Each service gets its own database (if it needs one)
- Services will never reach into another services database

## Pros

- We want each service to run independently of other services (looseley coupled services)
- Database schema/structure might change unexpectedly
- Some services might function more efficiently with different types of DB's

## Cons

- Business transactions that span multiple services are not straight forward to implement
- Implementing queries that join data that is now in multiple databases is challening
- Complexity of managing multiple different databases (SQL, NoSQL, ...)

## Problems

- Every Service same database
  - If database not available all serivces stop working
  - Scaling of the DB will be hard
- Service A directly accesses DB for Service B
  - If DB of Service B is down Service A will stop working (dependency between Service A and Service B)
  - If structure in Service B's database changes Service A will stop working
  ```{.json .numberLines}
  {
    "name": "Leander",
    "lastName": "Steiner"
  }

  {
    "firstName": "Leander",
    "lastName": "Steiner"
  }
  ```

# Inter-service Communication

## Slide with sources 2

```{.java .numberLines}
public record Thing(long id, String name) {
    public String getNameAndId() {
        return id + name;
    }
}
```
