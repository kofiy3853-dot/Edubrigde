package com.edubridge.shared.events;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.Acknowledgment;

public abstract class BaseEventConsumer<T> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    protected abstract void handleEvent(T event, Acknowledgment acknowledgment);

    protected abstract Class<T> getEventType();

    protected abstract String getTopic();

    public void onMessage(ConsumerRecord<String, Object> record, Acknowledgment acknowledgment) {
        try {
            log.info("Received event from topic: {}, partition: {}, offset: {}",
                record.topic(), record.partition(), record.offset());

            if (record.value() instanceof EventEnvelope envelope) {
                Object payload = envelope.payload();
                if (getEventType().isInstance(payload)) {
                    handleEvent(getEventType().cast(payload), acknowledgment);
                } else {
                    log.warn("Event type mismatch: expected {}, got {}",
                        getEventType().getSimpleName(), payload.getClass().getSimpleName());
                    acknowledgment.acknowledge();
                }
            } else {
                log.warn("Received non-envelope event from topic: {}", record.topic());
                acknowledgment.acknowledge();
            }
        } catch (Exception e) {
            log.error("Error processing event from topic: {}", record.topic(), e);
            handleFailure(record, e, acknowledgment);
        }
    }

    protected void handleFailure(ConsumerRecord<String, Object> record, Exception e, Acknowledgment acknowledgment) {
        log.error("Event processing failed, acknowledging to prevent infinite retry", e);
        acknowledgment.acknowledge();
    }
}
