package com.example.logisticapp.mapper;

import com.example.logisticapp.dto.TransportPlanDto;
import com.example.logisticapp.model.TransportPlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransportPlanMapper {

    List<TransportPlanDto> plansToDtos(List<TransportPlan> plans);
    List<TransportPlan> dtosToPlans(List<TransportPlanDto> dtos);
    @Mapping(target = "id", source = "transportPlanId")
    TransportPlanDto planToDto(TransportPlan transportPlan);
    TransportPlan dtoToPlan(TransportPlanDto transportPlanDto);

}
