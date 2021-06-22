# SSL

## Create certificate authority (CA)
```
openssl req -new -x509 -keyout ca-key -out ca-cert -days 365 -passin pass:test1234 -passout pass:test1234 -subj "/CN=localhost/OU=Divisi IT/O=PT. Terbaik/L=Bandung/ST=Indonesia/C=ID"
```

## Kafka Broker
### Create Keystore
```
# Create kafka.server.keystore.jks
keytool -noprompt -keystore kafka.server.keystore.jks -genkey -alias kafka -dname "CN=localhost, OU=Divisi IT, O=PT. Terbaik, L=Bandung, ST=Indonesia, C=ID" -storepass test1234 -keypass test1234

# Create certificate signing request (CSR)
keytool -noprompt -keystore kafka.server.keystore.jks -alias kafka -certreq -file cert-unsigned -storepass test1234

# Sign the CSR
openssl x509 -req -CA ca-cert -CAkey ca-key -in cert-unsigned -out cert-signed -days 365 -CAcreateserial -passin pass:test1234

# Import the CA into Keystore
keytool -noprompt -keystore kafka.server.keystore.jks -alias CARoot -import -file ca-cert -storepass test1234

# Import the signed certificate into Keystore
keytool -noprompt -keystore kafka.server.keystore.jks -alias kafka -import -file cert-signed -storepass test1234
```
### Create Truststore
```
# Import CA into server truststore
keytool -noprompt -keystore kafka.server.truststore.jks -alias CARoot -import -file ca-cert -storepass test1234
```

## Client Producer and Consumer
### Create Keystore
```
# Create kafka.client.keystore.jks
keytool -noprompt -keystore kafka.client.keystore.jks -genkey -alias client -dname "CN=localhost, OU=Divisi IT, O=PT. Terbaik, L=Bandung, ST=Indonesia, C=ID" -storepass test1234 -keypass test1234

# Create certificate signing request (CSR)
keytool -noprompt -keystore kafka.client.keystore.jks -alias client -certreq -file cert-unsigned -storepass test1234

# Sign the CSR
openssl x509 -req -CA ca-cert -CAkey ca-key -in cert-unsigned -out cert-signed -days 365 -CAcreateserial -passin pass:test1234

# Import the CA into Keystore
keytool -noprompt -keystore kafka.client.keystore.jks -alias CARoot -import -file ca-cert -storepass test1234

# Import the signed certificate into Keystore
keytool -noprompt -keystore kafka.client.keystore.jks -alias client -import -file cert-signed -storepass test1234
```
### Create Truststore
```
# Import CA into client truststore
keytool -noprompt -keystore kafka.client.truststore.jks -alias CARoot -import -file ca-cert -storepass test1234
```