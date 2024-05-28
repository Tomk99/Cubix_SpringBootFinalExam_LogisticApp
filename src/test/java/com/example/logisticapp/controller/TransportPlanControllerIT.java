package com.example.logisticapp.controller;

import com.example.logisticapp.config.LogisticConfig;
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
import java.util.Objects;
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
    @Autowired
    LogisticConfig config;

    String jwtTransportManager;
    String jwtAddressManager;

    TransportPlanDto transportPlanDto = new TransportPlanDto(0L, 100000, List.of(
            new Section(0L, 1,
                    new Milestone(0L, LocalDateTime.now(), new Address(0L, 36, 3300, "Eger", "Rakoczi", 44, 0, 0)),
                    new Milestone(0L, LocalDateTime.now(), new Address(0L, 36, 3400, "Mezokovesd", "Mindszenty", 12, 0, 0))
            ),
            new Section(0L, 2,
                    new Milestone(0L, LocalDateTime.now(), new Address(0L, 36, 3500, "Miskolc", "Soltesz Nagy Kalman", 40, 0, 0)),
                    new Milestone(0L, LocalDateTime.now(), new Address(0L, 36, 3600, "Ozd", "Alkotmany", 11, 0, 0)))
    ));

    @BeforeEach
    void init() {

        logisticUserRepository.deleteAllInBatch();
        transportPlanRepository.deleteAllInBatch();
        sectionRepository.deleteAllInBatch();
        milestoneRepository.deleteAllInBatch();
        addressRepository.deleteAllInBatch();

        logisticUserRepository.save(new LogisticUser(USER_TRANSPORT_MANAGER, passwordEncoder.encode(PASSWORD), Set.of("TransportManager")));
        logisticUserRepository.save(new LogisticUser(USER_ADDRESS_MANAGER, passwordEncoder.encode(PASSWORD), Set.of("AddressManager")));

        LoginDto body1 = new LoginDto();
        body1.setUsername(USER_TRANSPORT_MANAGER);
        body1.setPassword(PASSWORD);
        jwtTransportManager = webTestClient
                .post()
                .uri("/api/login")
                .bodyValue(body1)
                .exchange()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        LoginDto body2 = new LoginDto();
        body2.setUsername(USER_ADDRESS_MANAGER);
        body2.setPassword(PASSWORD);
        jwtAddressManager = webTestClient
                .post()
                .uri("/api/login")
                .bodyValue(body2)
                .exchange()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
    }

    @Test
    void testAddNewPlan() {

        List<TransportPlanDto> listBeforeCreate = getAll(jwtTransportManager);
        addNewPlan(transportPlanDto, jwtTransportManager).expectStatus().isOk();
        List<TransportPlanDto> listAfterCreate = getAll(jwtTransportManager);
        assertThat(listAfterCreate.size()-1).isEqualTo(listBeforeCreate.size());
    }

    @Test
    void testDelay() {
        MilestoneDelayDto milestoneDelayDto = new MilestoneDelayDto(0L, 20);
        addNewPlan(transportPlanDto,jwtTransportManager).expectStatus().isOk();
        List<TransportPlanDto> listBeforeDelay = getAll(jwtTransportManager);
        List<Long> startPointIds = Objects.requireNonNull(listBeforeDelay.stream().findFirst().orElse(null)).getSections().stream().map(Section::getStartPoint).map(Milestone::getMilestoneId).toList();
        List<Long> endPointIds = Objects.requireNonNull(listBeforeDelay.stream().findFirst().orElse(null)).getSections().stream().map(Section::getEndPoint).map(Milestone::getMilestoneId).toList();
        Long firstStartPoint = startPointIds.get(0);
        Long firstEndPoint = endPointIds.get(0);
        Long secondStartPoint = startPointIds.get(1);
        Long secondEndPoint = endPointIds.get(1);
        long id = Objects.requireNonNull(listBeforeDelay.stream().findFirst().orElse(null)).getId();

        milestoneDelayDto.setMilestoneId(firstStartPoint);
        addDelay(id,milestoneDelayDto,jwtTransportManager).expectStatus().isOk();
        List<TransportPlanDto> list1 = getAll(jwtTransportManager);
        milestoneDelayDto.setMilestoneId(firstEndPoint);
        addDelay(id,milestoneDelayDto,jwtTransportManager).expectStatus().isOk();
        List<TransportPlanDto> list2 = getAll(jwtTransportManager);
        milestoneDelayDto.setMilestoneId(secondStartPoint);
        addDelay(id,milestoneDelayDto,jwtTransportManager).expectStatus().isOk();
        List<TransportPlanDto> list3 = getAll(jwtTransportManager);
        milestoneDelayDto.setMilestoneId(secondEndPoint);
        addDelay(id,milestoneDelayDto,jwtTransportManager).expectStatus().isBadRequest();
        List<TransportPlanDto> list4 = getAll(jwtTransportManager);

        List<LocalDateTime> startPointDatesBefore = Objects.requireNonNull(listBeforeDelay.stream().findFirst().orElse(null)).getSections().stream().map(Section::getStartPoint).map(Milestone::getPlannedTime).toList();
        List<LocalDateTime> endPointDatesBefore = Objects.requireNonNull(listBeforeDelay.stream().findFirst().orElse(null)).getSections().stream().map(Section::getEndPoint).map(Milestone::getPlannedTime).toList();
        List<LocalDateTime> startPointDates1 = Objects.requireNonNull(list1.stream().findFirst().orElse(null)).getSections().stream().map(Section::getStartPoint).map(Milestone::getPlannedTime).toList();
        List<LocalDateTime> endPointDates1 = Objects.requireNonNull(list1.stream().findFirst().orElse(null)).getSections().stream().map(Section::getEndPoint).map(Milestone::getPlannedTime).toList();
        List<LocalDateTime> startPointDates2 = Objects.requireNonNull(list2.stream().findFirst().orElse(null)).getSections().stream().map(Section::getStartPoint).map(Milestone::getPlannedTime).toList();
        List<LocalDateTime> endPointDates2 = Objects.requireNonNull(list2.stream().findFirst().orElse(null)).getSections().stream().map(Section::getEndPoint).map(Milestone::getPlannedTime).toList();
        List<LocalDateTime> startPointDates3 = Objects.requireNonNull(list3.stream().findFirst().orElse(null)).getSections().stream().map(Section::getStartPoint).map(Milestone::getPlannedTime).toList();
        List<LocalDateTime> endPointDates3 = Objects.requireNonNull(list3.stream().findFirst().orElse(null)).getSections().stream().map(Section::getEndPoint).map(Milestone::getPlannedTime).toList();
        List<LocalDateTime> startPointDates4 = Objects.requireNonNull(list4.stream().findFirst().orElse(null)).getSections().stream().map(Section::getStartPoint).map(Milestone::getPlannedTime).toList();
        List<LocalDateTime> endPointDates4 = Objects.requireNonNull(list4.stream().findFirst().orElse(null)).getSections().stream().map(Section::getEndPoint).map(Milestone::getPlannedTime).toList();

        assertThat(endPointDates1.get(0)).isEqualTo(endPointDatesBefore.get(0).plusMinutes(milestoneDelayDto.getMilestoneDelay()));
        assertThat(startPointDates2.get(1)).isEqualTo(startPointDates1.get(1).plusMinutes(milestoneDelayDto.getMilestoneDelay()));
        assertThat(endPointDates3.get(1)).isEqualTo(endPointDates2.get(1).plusMinutes(milestoneDelayDto.getMilestoneDelay()));
    }

    @Test
    void testDelayWithWrongRole() {
        MilestoneDelayDto milestoneDelayDto = new MilestoneDelayDto(0L, 20);
        addNewPlan(transportPlanDto,jwtTransportManager).expectStatus().isOk();
        List<TransportPlanDto> listBeforeDelay = getAll(jwtTransportManager);
        List<Long> startPointIds = Objects.requireNonNull(listBeforeDelay.stream().findFirst().orElse(null)).getSections().stream().map(Section::getStartPoint).map(Milestone::getMilestoneId).toList();
        Long firstStartPoint = startPointIds.get(0);
        long id = Objects.requireNonNull(listBeforeDelay.stream().findFirst().orElse(null)).getId();

        milestoneDelayDto.setMilestoneId(firstStartPoint);
        addDelay(id,milestoneDelayDto,jwtAddressManager).expectStatus().isForbidden();
    }

    @Test
    void testDelayEffectToIncome() {
        MilestoneDelayDto milestoneDelayDto = new MilestoneDelayDto(0L, 20);
        addNewPlan(transportPlanDto,jwtTransportManager).expectStatus().isOk();
        List<TransportPlanDto> listBeforeDelay = getAll(jwtTransportManager);
        List<Long> startPointIds = Objects.requireNonNull(listBeforeDelay.stream().findFirst().orElse(null)).getSections().stream().map(Section::getStartPoint).map(Milestone::getMilestoneId).toList();
        Long firstStartPoint = startPointIds.get(0);
        long id = Objects.requireNonNull(listBeforeDelay.stream().findFirst().orElse(null)).getId();

        milestoneDelayDto.setMilestoneId(firstStartPoint);
        addDelay(id,milestoneDelayDto,jwtTransportManager).expectStatus().isOk();
        List<TransportPlanDto> list1 = getAll(jwtTransportManager);

        milestoneDelayDto.setMilestoneDelay(30+1);
        addDelay(id,milestoneDelayDto,jwtTransportManager).expectStatus().isOk();
        List<TransportPlanDto> list2 = getAll(jwtTransportManager);

        milestoneDelayDto.setMilestoneDelay(60+1);
        addDelay(id,milestoneDelayDto,jwtTransportManager).expectStatus().isOk();
        List<TransportPlanDto> list3 = getAll(jwtTransportManager);

        milestoneDelayDto.setMilestoneDelay(120+1);
        addDelay(id,milestoneDelayDto,jwtTransportManager).expectStatus().isOk();
        List<TransportPlanDto> list4 = getAll(jwtTransportManager);

        assertThat(list1.stream().map(TransportPlanDto::getIncome).findFirst().orElse(0)).isEqualTo(listBeforeDelay.stream().map(TransportPlanDto::getIncome).findFirst().orElse(0));
        assertThat(list2.stream().map(TransportPlanDto::getIncome).findFirst().orElse(0)*1.0).isEqualTo(list1.stream().map(TransportPlanDto::getIncome).findFirst().orElse(0)*((100-config.getDelay().getMin30())/100));
        assertThat(list3.stream().map(TransportPlanDto::getIncome).findFirst().orElse(0)*1.0).isEqualTo(list2.stream().map(TransportPlanDto::getIncome).findFirst().orElse(0)*((100-config.getDelay().getMin60())/100));
        assertThat(list4.stream().map(TransportPlanDto::getIncome).findFirst().orElse(0)*1.0).isEqualTo(list3.stream().map(TransportPlanDto::getIncome).findFirst().orElse(0)*((100-config.getDelay().getMin120())/100));
    }


    private List<TransportPlanDto> getAll(String jwt) {
        return webTestClient.get()
                .uri(BASE_URI)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwt))
                .exchange()
                .expectBodyList(TransportPlanDto.class)
                .returnResult()
                .getResponseBody();
    }
    private WebTestClient.ResponseSpec addNewPlan(TransportPlanDto dto, String jwt) {
        return webTestClient.post()
                .uri(BASE_URI)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwt))
                .bodyValue(dto)
                .exchange();
    }

    private WebTestClient.ResponseSpec addDelay(long id, MilestoneDelayDto milestoneDelayDto, String jwt) {
        return webTestClient.post()
                .uri(BASE_URI+"/"+id+"/delay")
                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwt))
                .bodyValue(milestoneDelayDto)
                .exchange();
    }
}
