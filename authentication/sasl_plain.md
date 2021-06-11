# Server Auth
## Zookeeper Config
#### zookeeper.properties
```
authProvider.1=org.apache.zookeeper.server.auth.SASLAuthenticationProvider
requireClientAuthScheme=sasl
jaasLoginRenew=3600000
```
#### zookeeper_jaas.conf
```
Server {
org.apache.zookeeper.server.auth.DigestLoginModule required
   user_super="zookeeper"
   user_admin="admin-secret";
};
```
## Kafka Server Config
#### server.properties
```
security.inter.broker.protocol=SASL_PLAINTEXT
sasl.mechanism.inter.broker.protocol=PLAIN
sasl.enabled.mechanisms=PLAIN
authorizer.class.name=kafka.security.auth.SimpleAclAuthorizer
allow.everyone.if.no.acl.found=true
auto.create.topics.enable=true
listeners=SASL_PLAINTEXT://<IP Address>:9092
advertised.listeners=SASL_PLAINTEXT://<IP Address>:9092
```
#### kafka_server_jaas.conf
```
KafkaServer {
org.apache.kafka.common.security.plain.PlainLoginModule required
username="admin"
password="admin-secret"
user_admin="admin-secret";
};
Client {
org.apache.zookeeper.server.auth.DigestLoginModule required
username="admin"
password="admin-secret";
};
```
## Add Environment Server
#### Zookeeper
```
# Linux
export KAFKA_OPTS="-Djava.security.auth.login.config=/config/zookeeper_jaas.conf"

# Windows
set KAFKA_OPTS="-Djava.security.auth.login.config=D:/config/zookeeper_jaas.conf"
```
#### Kafka
```
# Linux
export KAFKA_OPTS="-Djava.security.auth.login.config=/config/kafka_server_jaas.conf"

# Windows
set KAFKA_OPTS="-Djava.security.auth.login.config=/KAFKA_HOME/config/kafka_server_jaas.conf"
```
## Starting Server
```
bin/zookeeper-server-start.sh/bat -daemon config/zookeeper.properties
bin/kafka-server-start.sh/bat -daemon config/server.properties
```
# Client
## Producer and Consumer
#### producer.properties
```
security.protocol=SASL_PLAINTEXT
sasl.mechanism=PLAIN
bootstrap.servers=localhost:9092
compression.type=none
```
#### consumer.properties
```
security.protocol=SASL_PLAINTEXT
sasl.mechanism=PLAIN
```
#### kafka_client_jaas.conf
```
KafkaClient {
org.apache.kafka.common.security.plain.PlainLoginModule required
username="admin"
password="admin-secret";
};
Client {
  org.apache.zookeeper.server.auth.DigestLoginModule required
  username="admin"
  password="admin-secret";
};
```
## Add Environment Client
```
# Linux
export KAFKA_OPTS="-Djava.security.auth.login.config=/KAFKA_HOME/config/kafka_client_jaas.conf"

# Windows
set KAFKA_OPTS="-Djava.security.auth.login.config=/KAFKA_HOME/config/kafka_client_jaas.conf"
```
## Test Client
```
# Consumer
/bin/kafka-console-consumer.sh/bat --topic test-topic --from-beginning --consumer.config=config/consumer.properties --bootstrap-server=localhost:9092

# Producer
/bin/kafka-console-producer.sh/bat --broker-list localhost:9092 --topic test-topic --producer.config=config/producer.properties
```
# Java Client
