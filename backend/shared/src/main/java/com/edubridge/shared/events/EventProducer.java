package com.edubridge.shared.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EventProducer {

    private static final Logger log = LoggerFactory.getLogger(EventProducer.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public EventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public <T> CompletableFuture<SendResult<String, Object>> publish(String topic, String key, T event) {
        log.info("Publishing event to topic: {}, key: {}", topic, key);
        return kafkaTemplate.send(topic, key, event)
            .whenComplete((result, ex) -> {
                if (ex != null) {
                    log.error("Failed to publish event to topic: {}, key: {}", topic, key, ex);
                } else {
                    log.info("Published event to topic: {}, partition: {}, offset: {}",
                        topic,
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
                }
            });
    }

    public <T> CompletableFuture<SendResult<String, Object>> publish(String topic, T event) {
        return publish(topic, null, event);
    }
}
