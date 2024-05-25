package com.example.logisticapp.contoller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transportPlans")
public class TransportPlanController {

    @PostMapping("/{id}/delay")
    private void delay(@PathVariable long id) {

    }
}
