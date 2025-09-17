package com.leo.shoppr.controller;

import com.leo.shoppr.constant.AppConstant;
import com.leo.shoppr.dto.common.CustomResponse;
import com.leo.shoppr.dto.common.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @Value("${ping.message}")
    private String message;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(PingController.class);

    @GetMapping("/ping")
    public ResponseEntity<CustomResponse<Void>> ping() {
        CustomResponse<Void> response = CustomResponse.<Void>builder()
                .status(ResponseStatus.SUCCESS)
                .message(message)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/test-kafka")
    public ResponseEntity<CustomResponse<Void>> testKafka() throws InterruptedException {

        for (int i=0; i<100; i++) {
            double latitude = -90 + Math.random() * 180;   // [-90, +90]
            double longitude = -180 + Math.random() * 360; // [-180, +180]
            String location = String.format("lat: %.6f, long: %.6f", latitude, longitude);

            logger.info(location);
            kafkaTemplate.send(AppConstant.TEST_TOPIC, location);

            Thread.sleep(1500);
        }

        CustomResponse<Void> response = CustomResponse.<Void>builder()
                .status(ResponseStatus.SUCCESS)
                .message("Test data published")
                .build();

        return ResponseEntity.ok(response);
    }

}
