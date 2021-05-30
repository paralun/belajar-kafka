package org.paralun.serialization;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class PersonDeserializer implements Deserializer<Person> {

    public PersonDeserializer() {
    }

    @Override
    public Person deserialize(String s, byte[] bytes) {
        try {
            return bytes == null ? null : (Person) SerializationUtils.deserialize(bytes);
        }catch (Exception e) {
            throw new SerializationException("Error");
        }
    }
}
