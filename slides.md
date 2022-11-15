---
title: Microservices
author: Leander Steiner
date: 28.11.2022
transition: fade
theme: white
---

# Introduction

---

## Outline

- What are Microservices
- When to use Microservices
- Modeling Microservices
- Data Managements
- Service Communication

---

## Tools

- Git
- Containerization (Docker, Podman)
- Orchestration (Kubernetes)
- Provisioning (Terraform, Ansible)
- CI/CD (Jenkins, CircleCI, Github Actions)
- Databases (SQL, NoSQL)
- Message/Event Broker (Kafka, NATS, RabbitMQ)
- Monitoring & Tracing (Prometheus, Grafana, Zipkin)

---

# What are Microservices

---

## Microservice vs Monolith

![](img/microservices-vs-monolith.svg)

---

## Scaling

![](img/microservice-vs-monolith-scaling.svg)

---

# Test 3

## Test4

![](img/pre/unititled.svg)

---

# Slide with sources 2

```{.java .numberLines}
public record Thing(long id, String name) {
    public String getNameAndId() {
        return id + name;
    }
}
```
