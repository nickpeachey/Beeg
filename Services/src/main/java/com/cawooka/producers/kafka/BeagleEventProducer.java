package com.cawooka.producers.kafka;

import com.cawooka.Beagle;
import concrete.BeagleDto;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;

public class BeagleEventProducer  {

    private Properties properties;
    public BeagleEventProducer() {
        properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("acks", "1");
        properties.setProperty("retries", "10");

        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        properties.setProperty("schema.registry.url", "http://127.0.0.1:8081");
    }
    public boolean send(BeagleDto beagleDto) {
        KafkaProducer<String, Beagle> kafkaProducer = new KafkaProducer<String, Beagle>(properties);
        String topic = "beagle-registered";
        Random random = new Random();
        Beagle beagle = Beagle.newBuilder()
                .setFirstName("#{}")
                .setAge(random.nextInt(0, 10))
        .build();
        return false;
    }
}
