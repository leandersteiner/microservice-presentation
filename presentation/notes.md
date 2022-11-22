# Microservices

The term "Microservice Architecture" has sprung up over the last few years to describe a particular way of designing software applications as suites of independently deployable services.

A microservice is a tiny and independent software process that runs on its own deployment schedule and can be updated independently.

## Good points

- Not a single huge codebase
- More resilient/reliable (errors or bugs are scoped to one service, no other service fails)
- Easy to deploy small/frequent changes
- High availability through kubernetes clusters / auto scaling
- Easy to scale organization / small teams per service
- Multiple languages, frameworks or technologies
- Fast pipelines, builds and tests due to smaller codebases per service

## Bad points

- Hard to understand full application flow / many containers, dbs, applications, etc.
- Hard to debug due to application flow through multiple services
- Distributed systems are complex

## Tooling

- Git
- Docker
- Docker Compose
- Kubernetes
- Terraform/Ansible
- CI/CD
- MongoDB / Postgres
- RabbitMQ / Nats / Kafka
- Jenkins / CircleCI / Github Actions
- Prometheus / Grafana
- Zipkin

## Designing microservices

- Not very different from designing monolithic applications
- Don't over design / future proof your architecture
- Continuous refactoring
- Let a good design emerge naturally

### Relevant principles

- Single responsibility principle
- Loose coupling
- High cohesion
- Domain Driven Design

### Violated Principles

- Don't repeat yourself (DRY)
- Immediate Consistency

## Event-Driven Microservices

Compute resources can be easily acquired and released on-demand, enabling the easy creation and management of microservices. Microservices can store and manage their data according to their own needs, and do so at a scale that was previously limited to batch-based big-data solutions.

In a modern event-driven microservices architecture, systems communicate by issuing and consuming events. These events are not destroyed upon consumption as in message-passing systems, but instead remain readily available for other consumers to read as they require.

These services consume events from input event streams; apply their specific business logic; and may emit their own output events, provide data for request-response access, communicate with a thirdparty API, or perform other required actions.

### Minimum DDD

#### Domain

The problem space that a business occupies and provides solutions to. This
encompasses everything that the business must contend with, including rules,
processes, ideas, business-specific terminology, and anything related to its problem
space, regardless of whether or not the business concerns itself with it. The
domain exists regardless of the existence of the business.

#### Subdomain

A component of the main domain. Each subdomain focuses on a specific subset
of responsibilities and typically reflects some of the business’s organizational
structure (such as Warehouse, Sales, and Engineering). A subdomain can be seen
as a domain in its own right. Subdomains, like the domain itself, belong to the
problem space.

#### Domain model

An abstraction of the actual domain useful for business purposes. The pieces and
properties of the domain that are most important to the business are used to generate
the model. The main domain model of an business is discernible through
the products the business provides its customers, the interfaces by which customers
interact with the products, and the various other processes and functions by
which the business fulfills its stated goals. Models often need to be refined as the
domain changes and as business priorities shift. A domain model is part of the
solution space, as it is a construct the business uses to solve problems.

#### Bounded Context

The logical boundaries, including the inputs, outputs, events, requirements, processes,
and data models, relevant to the subdomain. While ideally a bounded
context and a subdomain will be in complete alignment, legacy systems, technical
debt, and third-party integrations often create exceptions. Bounded contexts are
also a property of the solution space and have a significant impact on how microservices
interact with one another.

---

Bounded contexts should be highly cohesive. The internal operations of the context
should be intensive and closely related, with the vast majority of communication
occurring internally rather than cross-boundary. Having highly cohesive responsibilities
allows for reduced design scope and simpler implementations.
Connections between bounded contexts should be loosely coupled, as changes made
within one bounded context should minimize or eliminate the impact on neighboring
contexts. A loose coupling can ensure that requirement changes in one context do
not propagate a surge of dependent changes to neighboring contexts.

---

### Fundamentals

An event-driven microservice is a small application built to fulfill a specific bounded
context. Consumer microservices consume and process events from one or more
input event streams, whereas producer microservices produce events to event streams
for other services to consume. It is common for an event-driven microservice to be a
consumer of one set of input event streams and a producer of another set of output
event streams.

Events can be anything that is important to the business.

Events are typically represented using a key/value format.

### Communication and Data Contracts

Contracts: Avro, Thrift or Protobuf (Kafka supports JSON, Protobuf and Avro)

```
TypeEnum: Book, Movie
ActionEnum: Click

ProductEngagement {
  productId: Long,
  productType: TypeEnum,
  actionType: ActionEnum
}
```

New Requirement: who watched trailer before the movie

```
ProductEngagement {
  productId: Long,
  productType: TypeEnum,
  actionType: ActionEnum,
  //Only applies to type=Movie
  watchedTrailer: {null, Boolean}
}
```

New Requirement: add Bookmarks to books

```
TypeEnum: Book, Movie
ActionEnum: Click, Bookmark

ProductEngagement {
  productId: Long,
  productType: TypeEnum,
  actionType: ActionEnum,
  //Only applies to type=Movie
  watchedTrailer: {null, Boolean},
  //Only applies to productType=Book,actionType=Bookmark
  pageId: {null, Int}
}
```

Split up:

```
MovieClick {
  movieId: Long,
  watchedTrailer: Boolean
}

BookClick {
  bookId: Long
}

BookBookmark {
  bookId: Long,
  pageId: Int
}
```

### Event-Driven Processing

Event-driven microservies follow at a minimum the same three steps:

1. Consume an event from an input event stream
2. Process that event
3. Produce any necessary output events

```java
Consumer consumerClient = new Consumer(topic, group, ...);
Producer producerClient = new Producer(topic, ...);

while(true) {
  InputEvent event = consumerClient.pollOneEvent(inputEventStream);
  OutputEvent output = processEvent(event);
  producerClient.produceEventToStream(outputEventStream, output);
}
```

## Resources

https://www.developertoarchitect.com/lessons-microservices.html
