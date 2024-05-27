package com.example.logisticapp.contoller;

import com.example.logisticapp.dto.LoginDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/api/login")
    private String login(@RequestBody LoginDto loginDto) {
    }
}
