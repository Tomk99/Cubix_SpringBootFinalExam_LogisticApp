package com.example.logisticapp.controller;

import com.example.logisticapp.dto.*;
import com.example.logisticapp.model.Address;
import com.example.logisticapp.model.LogisticUser;
import com.example.logisticapp.model.Milestone;
import com.example.logisticapp.model.Section;
import com.example.logisticapp.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class TransportPlanControllerIT {
    private static final String BASE_URI = "/api/transportPlans";
    public static final String USER_ADDRESS_MANAGER = "address1";
    public static final String USER_TRANSPORT_MANAGER = "transport1";
    public static final String PASSWORD = "pass";

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    TransportPlanRepository transportPlanRepository;
    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    MilestoneRepository milestoneRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    LogisticUserRepository logisticUserRepository;

    String jwt;

    @BeforeEach
    void init() {

        logisticUserRepository.save(new LogisticUser(USER_TRANSPORT_MANAGER, passwordEncoder.encode(PASSWORD), Set.of("TransportManager")));

        LoginDto body = new LoginDto();
        body.setUsername(USER_TRANSPORT_MANAGER);
        body.setPassword(PASSWORD);
        jwt = webTestClient
                .post()
                .uri("/api/login")
                .bodyValue(body)
                .exchange()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
    }

    @Test
    void testAddNewPlan() {
        TransportPlanDto transportPlanDto = new TransportPlanDto(0L, 100000, List.of(
                new Section(0L, 1,
                        new Milestone(0L, LocalDateTime.now(), new Address(0L, 36, 3300, "Eger", "Rakoczi", 44, 0, 0)),
                        new Milestone(0L, LocalDateTime.now(), new Address(0L, 36, 3400, "Mezokovesd", "Mindszenty", 12, 0, 0))
                ),
                new Section(0L, 2,
                        new Milestone(0L, LocalDateTime.now(), new Address(0L, 36, 3500, "Miskolc", "Soltesz Nagy Kalman", 40, 0, 0)),
                        new Milestone(0L, LocalDateTime.now(), new Address(0L, 36, 3600, "Ozd", "Alkotmany", 11, 0, 0)))
        ));
        List<TransportPlanDto> listBeforeCreate = getAll();
        addNewPlan(transportPlanDto).expectStatus().isOk();
        List<TransportPlanDto> listAfterCreate = getAll();
        assertThat(listAfterCreate.size()-1).isEqualTo(listBeforeCreate.size());
    }

    private List<TransportPlanDto> getAll() {
        return webTestClient.get()
                .uri(BASE_URI)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwt))
                .exchange()
                .expectBodyList(TransportPlanDto.class)
                .returnResult()
                .getResponseBody();
    }
    private WebTestClient.ResponseSpec addNewPlan(TransportPlanDto dto) {
        return webTestClient.post()
                .uri(BASE_URI)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwt))
                .bodyValue(dto)
                .exchange();
    }
}
