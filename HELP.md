
## notes
pom packaging vs jar packaging
create a container for sub modules for jar packaging

relativePath
set empty for external projects, Use only for local parent projects.

dependencyManagement
Unifies the version for dependencies in the child modules, it doesn't need to download these.

order-container
create runable JAR file



degraph maven plugin
https://github.com/ferstl/depgraph-maven-plugin

graphviz

mvn com.github.ferstl:depgraph-maven-plugin:graph

mvn com.github.ferstl:depgraph-maven-plugin:graph:aggregate -DcreateImage=true -DreduceEdges=false -Dscope=compile "-Dincludes=com.food.ordering.system*:*"

Visualize dependencies:
https://github.com/ferstl/depgraph-maven-plugin
mvn com.github.ferstl:depgraph-maven-plugin:aggregate -DcreateImage=true -DreduceEdges=false -Dscope=compile "-Dincludes=com.food.ordering.system*:*"


Dependency Inversion
DIP leads to loosely coupled systems and an independent domain layer, by inverting any runtime dependency.


Which ones are concepts of the tactical DDD?

A.) Aggregate: Group of entities that are logically related.

B.) Aggregate root: Root entity to keep the aggregate in a consistent state.

C.) Entity: Core domain objects.

D.) Value object: Used to bring context to the value.

E.) Domain Event: Used to decouple the different domains. It will notify the other domains based on result of business logic.



entity
+ different by ID
+ validation in here
+ immutable class: Order
+ BaseEntity: OrderItem, Product
  + have unique identifier
  + An entity class contain the methods to complete critical business rules.
  + An entity class can act as an Aggregate Root, and in that case forcing all business invariants is responsibility
    + of that entity.
+ AggregateRoot: Order Entity, Customer Entity
  + immutable ? D: Entity classes do not have to be immutable. In fact they have state changes methods to run during business logic.
  + compare differently by ID ?

valueobject
+ immutable value
+ in common-domain if it uses in many services
+ compare by value


common-domain
+ provide domain basic for another service for using

Domain Service:
+ should have behaviors: updateProduct(), createProduct(), delete Product()
+ use behavior's AggregateRoot
+ in Clean Architecture: Domain Service could be matched to use-cases. bc use-cases describe as the components that drive business entities

Application Service:
+ has no business logic inside.
+ expose to outside through an interface. (use-cases are not exposed.)

where to fire the event?
In Application Service. Domain layer should not know about how to fire the event (at Domain Service).
It should create and return the event after running business logic.

Repository: CustomerRepository, OrderRepo, RestaurantRepo 
+ will be implemented in dataaccess adapters.



Note: 
+ save order in local database and fire the event (automatically) 
+ use Saga and Outbox
+ before fire make sure the changes are commited in local database 


TransactionalEventListener is a spring annotation that listens an event that is fired from a transactional method. And it only process the event if the transactional operation is completed successfully.

### kafka
Kafka manager: port 9000
Kafka registry: 8081



Zookeeper: Test ruok if server is running on non-error state. -> imok , else not response
echo ruok | nc localhost 2181
imok

run docker for kafka cluster
docker-compose -f common.yml -f zookeeper.yml up -d
docker-compose -f common.yml -f kafka_cluster.yml up -d


init kafka topics
docker-compose -f common.yml -f init_kafka.yml up -d


stop docker : kafka cluster
docker-compose -f common.yml -f kafka_cluster.yml down
docker-compose -f common.yml -f zookeeper.yml down

Kafka Manager
localhost:9000
add cluster name: food-ordering-system-cluster
Cluster Zookeeper Hosts: zookeeper:2181

