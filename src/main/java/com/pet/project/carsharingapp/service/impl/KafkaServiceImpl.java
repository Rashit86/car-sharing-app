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

import java.util.HashMap;
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

    @Override
    public void reserve(String userName, CarModelEnum carModel) {
        CarRequest car = new CarRequest();
        car.setUserName(userName);
        car.setCarModel(carModel);
        car.setRequiredAction(RequiredActionEnum.RESERVE);

        logger.info("Sending json = '{}' to topic = '{}'", car, topicName);
        getKafkaTemplate().send(topicName, car);
    }

    @Override
    public void cancel(String userName, CarModelEnum carModel) {
        CarRequest car = new CarRequest();
        car.setUserName(userName);
        car.setCarModel(carModel);
        car.setRequiredAction(RequiredActionEnum.CANCEL_RESERVATION);

        logger.info("Sending json = '{}' to topic = '{}'", car, topicName);
        getKafkaTemplate().send(topicName, car);
    }

    private KafkaTemplate<String, CarRequest> getKafkaTemplate() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(configProps));
    }
}
