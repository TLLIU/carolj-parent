package idv.tlliu.springboot.concurrent.web;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.MappingConstants.ComponentModel;

import idv.tlliu.springboot.concurrent.domain.SampleEntity;
import idv.tlliu.springboot.concurrent.web.dtos.SampleEditDto;
import idv.tlliu.springboot.concurrent.web.dtos.SampleResponseDto;

@Mapper(componentModel = ComponentModel.SPRING)
public interface SampleMapper {

	@Mapping(target = "mappedLabel", source = "label")
	SampleResponseDto toDto(SampleEntity domain);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "label", source = "mappedLabel")
    void update(@MappingTarget SampleEntity entity, SampleEditDto dto);

}