package com.leo.shoppr.controller;

import com.leo.shoppr.response.CustomResponse;
import com.leo.shoppr.response.ResponseStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/ping")
    public ResponseEntity<CustomResponse<Void>> ping() {
        CustomResponse<Void> response = new CustomResponse<>();

        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("Shoppr is Live!");

        return ResponseEntity.ok(response);
    }


}
