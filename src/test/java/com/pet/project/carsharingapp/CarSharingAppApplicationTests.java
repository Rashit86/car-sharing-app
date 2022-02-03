//package com.pet.project.carsharingapp;
//
//import com.pet.project.carsharingapp.consumer.KafkaConsumer;
//import com.pet.project.carsharingapp.controller.api.CarSharingService;
//import com.pet.project.carsharingapp.enums.CarModelEnum;
//import org.hamcrest.MatcherAssert;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.kafka.test.context.EmbeddedKafka;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.TimeUnit;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.hamcrest.Matchers.equalTo;
//
//@SpringBootTest
//@DirtiesContext
//@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
//@ActiveProfiles("test")
//class CarSharingAppApplicationTests {
//
//    @Autowired
//    private KafkaConsumer consumer;
//
//    @Autowired
//    private CarSharingService carSharingService;
//
//    @Test
//    void givenEmbeddedKafkaBroker_whenReserveCar_thenMessageReceived() throws Exception {
//        carSharingService.reserve("userName", CarModelEnum.BMW);
//        consumer.getLatch().await(10, TimeUnit.SECONDS);
//
//        MatcherAssert.assertThat(consumer.getLatch().getCount(), equalTo(0L));
//        MatcherAssert.assertThat(consumer.getPayload(), containsString("embedded-test-topic"));
//    }
//
//    @Test
//    void givenEmbeddedKafkaBroker_whenCancelCarReservation_thenMessageReceived() throws Exception {
//        carSharingService.cancel("userName", CarModelEnum.BMW);
//        CountDownLatch latch = consumer.getLatch();
//        latch.await(10, TimeUnit.SECONDS);
//
//        MatcherAssert.assertThat(consumer.getLatch().getCount(), equalTo(0L));
//        MatcherAssert.assertThat(consumer.getPayload(), containsString("embedded-test-topic"));
//    }
//
//}
