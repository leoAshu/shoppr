package com.leo.shoppr.controller;

import com.leo.shoppr.dto.common.CustomResponse;
import com.leo.shoppr.dto.common.ResponseStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @Value("${ping.message}")
    private String message;

    @GetMapping("/ping")
    public ResponseEntity<CustomResponse<Void>> ping() {
        CustomResponse<Void> response = CustomResponse.<Void>builder()
                .status(ResponseStatus.SUCCESS)
                .message(message)
                .build();

        return ResponseEntity.ok(response);
    }


}
