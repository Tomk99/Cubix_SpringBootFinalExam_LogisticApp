package com.example.logisticapp.mapper;

import com.example.logisticapp.dto.SectionDto;
import com.example.logisticapp.model.Section;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SectionMapper {
    @Mapping(target = "id", source = "sectionId")
    SectionDto sectionToDto(Section section);
    @InheritInverseConfiguration
    Section dtoToSection(SectionDto sectionDto);
    List<SectionDto> sectionsToDtos(List<Section> sections);
}
