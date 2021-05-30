package org.paralun.serialization;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class PersonSerializer implements Serializer<Person> {

    public PersonSerializer() {
    }

    @Override
    public byte[] serialize(String s, Person person) {
        try {
            return person == null ? null : SerializationUtils.serialize(person);
        }catch (Exception e) {
            throw new SerializationException("Error");
        }
    }
}
