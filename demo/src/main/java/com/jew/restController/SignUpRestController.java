package com.jew.restController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jew.mapper.SignUpMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SignUpRestController {

    @Autowired
    SignUpMapper signUpMapper;

    @GetMapping("/checkUserid")
    public ResponseEntity<?> checkUserId(@RequestParam String userId) {
        try {
            int count = signUpMapper.checkUserId(userId);
            boolean available = count == 0;
            return ResponseEntity.ok(Map.of("available", available));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }
    }
}
