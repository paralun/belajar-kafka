# SSL

## Create certificate authority (CA)
```
openssl req -new -x509 -keyout ca-key -out ca-cert -days 365 -passin pass:test1234 -passout pass:test1234 -subj "/CN=ca-cert/OU=Divisi IT/O=PT. Terbaik/L=Bandung/ST=Indonesia/C=ID"
```
## Zookeeper Server
### Create Keystore Zookeeper Server
```
# Create zookeeper.server.keystore.jks
keytool -noprompt -keystore zookeeper.server.keystore.jks -genkey -keyalg RSA -alias zookeeper -dname "CN=localhost, OU=Divisi IT, O=PT. Terbaik, L=Bandung, ST=Indonesia, C=ID" -storepass test1234 -keypass test1234 -ext SAN=DNS:localhost

# Create certificate signing request (CSR)
keytool -noprompt -keystore zookeeper.server.keystore.jks -alias zookeeper -certreq -file cert-unsigned-zookeeper -storepass test1234

# Sign the CSR
openssl x509 -req -CA ca-cert -CAkey ca-key -in cert-unsigned-zookeeper -out cert-signed-zookeeper -days 365 -CAcreateserial -passin pass:test1234

# Import the CA into Keystore
keytool -noprompt -keystore zookeeper.server.keystore.jks -alias ca-cert -import -file ca-cert -storepass test1234

# Import the signed certificate into Keystore
keytool -noprompt -keystore zookeeper.server.keystore.jks -alias zookeeper -import -file cert-signed-zookeeper -storepass test1234
```
### Create Truststore Zookeeper Server
```
# Import CA into server truststore
keytool -noprompt -keystore zookeeper.server.truststore.jks -alias ca-cert -import -file ca-cert -storepass test1234
```

## Kafka Broker
### Create Keystore Broker Server
```
# Create kafka.server.keystore.jks
keytool -noprompt -keystore kafka.server.keystore.jks -genkey -keyalg RSA -alias kafka -dname "CN=localhost, OU=Divisi IT, O=PT. Terbaik, L=Bandung, ST=Indonesia, C=ID" -storepass test1234 -keypass test1234 -ext SAN=DNS:localhost

# Create certificate signing request (CSR)
keytool -noprompt -keystore kafka.server.keystore.jks -alias kafka -certreq -file cert-unsigned-kafka -storepass test1234

# Sign the CSR
openssl x509 -req -CA ca-cert -CAkey ca-key -in cert-unsigned-kafka -out cert-signed-kafka -days 365 -CAcreateserial -passin pass:test1234

# Import the CA into Keystore
keytool -noprompt -keystore kafka.server.keystore.jks -alias ca-cert -import -file ca-cert -storepass test1234

# Import the signed certificate into Keystore
keytool -noprompt -keystore kafka.server.keystore.jks -alias kafka -import -file cert-signed-kafka -storepass test1234
```
### Create Truststore Broker Server
```
# Import CA into server truststore
keytool -noprompt -keystore kafka.server.truststore.jks -alias ca-cert -import -file ca-cert -storepass test1234
```

## Zookeeper Client
### Create Keystore Zookeeper Client
```
# Create zookeeper.client.keystore.jks
keytool -noprompt -keystore zookeeper.client.keystore.jks -genkey -keyalg RSA -alias zookeeper-client -dname "CN=localhost, OU=Divisi IT, O=PT. Terbaik, L=Bandung, ST=Indonesia, C=ID" -storepass test1234 -keypass test1234 -ext SAN=DNS:localhost

# Create certificate signing request (CSR)
keytool -noprompt -keystore zookeeper.client.keystore.jks -alias zookeeper-client -certreq -file cert-unsigned-zookeeper-client -storepass test1234

# Sign the CSR
openssl x509 -req -CA ca-cert -CAkey ca-key -in cert-unsigned-zookeeper-client -out cert-signed-zookeeper-client -days 365 -CAcreateserial -passin pass:test1234

# Import the CA into Keystore
keytool -noprompt -keystore zookeeper.client.keystore.jks -alias ca-cert -import -file ca-cert -storepass test1234

# Import the signed certificate into Keystore
keytool -noprompt -keystore zookeeper.client.keystore.jks -alias zookeeper-client -import -file cert-signed-zookeeper-client -storepass test1234
```
### Create Truststore Zookeeper Client
```
# Import CA into server truststore
keytool -noprompt -keystore zookeeper.client.truststore.jks -alias ca-cert -import -file ca-cert -storepass test1234
```

## Client Producer and Consumer
### Create Keystore Broker Client
```
# Create kafka.client.keystore.jks
keytool -noprompt -keystore kafka.client.keystore.jks -genkey -keyalg RSA -alias kafka-client -dname "CN=localhost, OU=Divisi IT, O=PT. Terbaik, L=Bandung, ST=Indonesia, C=ID" -storepass test1234 -keypass test1234 -ext SAN=DNS:localhost

# Create certificate signing request (CSR)
keytool -noprompt -keystore kafka.client.keystore.jks -alias kafka-client -certreq -file cert-unsigned-kafka-client -storepass test1234

# Sign the CSR
openssl x509 -req -CA ca-cert -CAkey ca-key -in cert-unsigned-kafka-client -out cert-signed-kafka-client -days 365 -CAcreateserial -passin pass:test1234

# Import the CA into Keystore
keytool -noprompt -keystore kafka.client.keystore.jks -alias ca-cert -import -file ca-cert -storepass test1234

# Import the signed certificate into Keystore
keytool -noprompt -keystore kafka.client.keystore.jks -alias kafka-client -import -file cert-signed-kafka-client -storepass test1234
```
### Create Truststore Broker Client
```
# Import CA into client truststore
keytool -noprompt -keystore kafka.client.truststore.jks -alias ca-cert -import -file ca-cert -storepass test1234
```