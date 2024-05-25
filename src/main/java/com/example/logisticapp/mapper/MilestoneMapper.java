package com.example.logisticapp.mapper;

import com.example.logisticapp.dto.MilestoneDto;
import com.example.logisticapp.model.Milestone;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MilestoneMapper {

    @Mapping(target = "id", source = "milestoneId")
    MilestoneDto milestoneToDto(Milestone milestone);
    @InheritInverseConfiguration
    Milestone dtoToMilestone(MilestoneDto milestoneDto);
    List<MilestoneDto> milestonesToDtos(List<Milestone> milestones);
    List<Milestone> dtosToMilestones(List<MilestoneDto> milestoneDtos);
}
