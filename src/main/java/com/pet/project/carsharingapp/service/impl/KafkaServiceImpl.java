package com.pet.project.carsharingapp.service.impl;

import com.pet.project.carsharingapp.enums.CarModelEnum;
import com.pet.project.carsharingapp.enums.RequiredActionEnum;
import com.pet.project.carsharingapp.request.CarRequest;
import com.pet.project.carsharingapp.service.api.KafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    Logger logger = LoggerFactory.getLogger(KafkaServiceImpl.class);

    @Value("${car-sharing-app.kafka.topic-name}")
    private String topicName;

    @Value("${car-sharing-app.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${car-sharing-app.kafka.key-serializer}")
    private Class keySerializer;

    @Value("${car-sharing-app.kafka.value-serializer}")
    private Class valueSerializer;

    @Value("${car-sharing-app.kafka.acks}")
    private String acks;

    @Value("${car-sharing-app.kafka.retries}")
    private String retries;

    @Value("${car-sharing-app.kafka.linger_ms}")
    private String lingerMs;

    @Value("${car-sharing-app.kafka.enable_idempotence}")
    private String enableIdempotence;

    @Override
    public void reserve(String userName, CarModelEnum carModel) {
        CarRequest car = new CarRequest();
        car.setUserName(userName);
        car.setCarModel(carModel);
        car.setRequiredAction(RequiredActionEnum.RESERVE);
        car.setTime(Instant.now().toString());

        logger.info("Sending json = '{}' to topic = '{}'", car, topicName);
        getKafkaTemplate().send(topicName, car.getCarModel().toString(), car);
    }

    @Override
    public void cancel(String userName, CarModelEnum carModel) {
        CarRequest car = new CarRequest();
        car.setUserName(userName);
        car.setCarModel(carModel);
        car.setRequiredAction(RequiredActionEnum.CANCEL_RESERVATION);
        car.setTime(Instant.now().toString());

        logger.info("Sending json = '{}' to topic = '{}'", car, topicName);
        getKafkaTemplate().send(topicName, car.getCarModel().toString(), car);
    }

    private KafkaTemplate<String, CarRequest> getKafkaTemplate() {
        return new KafkaTemplate<>(
                new DefaultKafkaProducerFactory<>(
                        Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
                                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer,
                                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer,
                                ProducerConfig.ACKS_CONFIG, acks,
                                ProducerConfig.RETRIES_CONFIG, retries,
                                ProducerConfig.LINGER_MS_CONFIG, lingerMs,
                                ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, enableIdempotence)));
    }
}
