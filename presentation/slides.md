---
title: Microservices
author: Leander Steiner
date: 29.11.2022
transition: fade
theme: white
---

# Introduction

---

## Outline

- What are Microservices
- Inter-service Communication
- Modeling Microservices
- Data Management
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

![](img/microservice-example.svg)

---

![](img/microservices-vs-monolith.svg)

---

![](img/microservice-vs-monolith-scaling.svg)


---

# Inter-service Communication

---

# Slide with sources 2

```{.java .numberLines}
public record Thing(long id, String name) {
    public String getNameAndId() {
        return id + name;
    }
}
```
