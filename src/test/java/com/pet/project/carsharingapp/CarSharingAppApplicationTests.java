package com.pet.project.carsharingapp;

import com.pet.project.carsharingapp.consumer.KafkaConsumer;
import com.pet.project.carsharingapp.controller.api.CarSharingService;
import com.pet.project.carsharingapp.enums.CarModelEnum;
import com.pet.project.carsharingapp.enums.RequiredActionEnum;
import com.pet.project.carsharingapp.request.CarRequest;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
@ActiveProfiles("test")
@AutoConfigureMockMvc
class CarSharingAppApplicationTests {

    @Value("${car-sharing-app.topic-name.car-request}")
    private String carRequestTopicName;

    @Autowired
    private KafkaConsumer consumer;

    @Autowired
    private CarSharingService carSharingService;

    @Test
    @WithMockUser(username = "test", authorities = "write")
    void reserveTest() throws Exception {
        CarRequest carRequest = new CarRequest();
        carRequest.setUserName("userName");
        carRequest.setCarModel(CarModelEnum.BMW);
        carRequest.setRequiredAction(RequiredActionEnum.RESERVE);

        carSharingService.rent(carRequest);
        consumer.getLatch().await(10, TimeUnit.SECONDS);

        MatcherAssert.assertThat(consumer.getLatch().getCount(), equalTo(0L));
        MatcherAssert.assertThat(consumer.getPayload(), containsString(carRequestTopicName));
    }

    @Test
    @WithMockUser(username = "test", authorities = "write")
    void cancelReservationTest() throws Exception {
        CarRequest carRequest = new CarRequest();
        carRequest.setUserName("userName");
        carRequest.setCarModel(CarModelEnum.BMW);
        carRequest.setRequiredAction(RequiredActionEnum.CANCEL_RESERVATION);
        carSharingService.rent(carRequest);
        CountDownLatch latch = consumer.getLatch();
        latch.await(10, TimeUnit.SECONDS);

        MatcherAssert.assertThat(consumer.getLatch().getCount(), equalTo(0L));
        MatcherAssert.assertThat(consumer.getPayload(), containsString("car_request_topic"));
    }

}
