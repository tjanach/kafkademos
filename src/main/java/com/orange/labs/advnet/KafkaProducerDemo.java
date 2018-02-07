package com.orange.labs.advnet;

import kafka.serializer.StringEncoder;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.*;

public class KafkaProducerDemo {

public static void main(String[] args){
    Properties props = new Properties();
    //props.put("serializer.class", "kafka.serializer.StringEncoder");
    props.put("key.serializer", "kafka.serializer.StringEncoder");
    props.put("value.serializer", "kafka.serializer.StringEncoder");
    props.put("metadata.broker.list", "192.168.99.100:9093");
    props.put("bootstrap.servers", "192.168.99.100:9093");
    props.put("producer.type","sync");
    props.put("request.required.acks",1);
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
            "192.168.99.100:9092");
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
            "org.apache.kafka.common.serialization.ByteArraySerializer");
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
            "org.apache.kafka.common.serialization.ByteArraySerializer");

    KafkaProducer kafkaProducer = new KafkaProducer(props);
    List<String> list = Arrays.asList("ala", "ma", "kota");
    list.forEach(p -> kafkaProducer.send(new ProducerRecord<byte[],byte[]>("test-java", "key".getBytes(), p.getBytes())));

    List partitionInfoList = kafkaProducer.partitionsFor("test-java");
    Map metrics = kafkaProducer.metrics();
    kafkaProducer.close();

}

}
