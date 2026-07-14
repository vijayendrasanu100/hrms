package com.hrms.department.mapper;

import com.hrms.department.dto.DepartmentRequestDTO;
import com.hrms.department.dto.DepartmentResponseDTO;
import com.hrms.department.entity.DepartmentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentEntity toEntity(DepartmentRequestDTO requestDTO);

    DepartmentResponseDTO toResponseDTO(DepartmentEntity entity);
}