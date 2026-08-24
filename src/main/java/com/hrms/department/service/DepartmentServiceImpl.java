package com.hrms.department.service;

import com.hrms.common.exception.DuplicateResourceException;
import com.hrms.common.exception.ResourceNotFoundException;
import com.hrms.common.util.DepartmentCodeGenerator;
import com.hrms.department.dto.DepartmentRequestDTO;
import com.hrms.department.dto.DepartmentResponseDTO;
import com.hrms.department.entity.DepartmentEntity;
import com.hrms.department.mapper.DepartmentMapper;
import com.hrms.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO requestDTO) {

        // Check duplicate department name
        if (departmentRepository.findByDepartmentName(requestDTO.getDepartmentName()).isPresent()) {
            throw new DuplicateResourceException("Department Name already exists");
        }

        // Start with the first global number
        int maxNumber = departmentRepository.findMaxDepartmentNumber();
        int number = maxNumber + 1;

        String departmentCode =
                DepartmentCodeGenerator.generateCode(
                        requestDTO.getDepartmentName(),
                        number
                );

        // Check generated department code
        boolean codeExists =
                departmentRepository.existsByDepartmentCode(departmentCode);

        if (codeExists) {
            throw new DuplicateResourceException("Department Code already exists");
        } else {
            // Code is available — continue with creation
        }

        DepartmentEntity entity = departmentMapper.toEntity(requestDTO);

        // Code is generated automatically
        entity.setDepartmentCode(departmentCode);

        DepartmentEntity savedDepartment = departmentRepository.save(entity);

        return departmentMapper.toResponseDTO(savedDepartment);
    }

    @Override
    public Page<DepartmentResponseDTO> getAllDepartments(Pageable pageable) {

        return departmentRepository.findAll(pageable)
                .map(departmentMapper::toResponseDTO);
    }

    @Override
    public DepartmentResponseDTO getDepartmentById(Long id) {

        DepartmentEntity entity = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with id : " + id
                        ));

        return departmentMapper.toResponseDTO(entity);
    }

    @Override
    public DepartmentResponseDTO updateDepartment(
            Long id,
            DepartmentRequestDTO requestDTO) {

        DepartmentEntity entity = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with id : " + id
                        ));

        entity.setDepartmentName(requestDTO.getDepartmentName());
        entity.setDescription(requestDTO.getDescription());
        entity.setStatus(requestDTO.getStatus());

        DepartmentEntity updatedDepartment =
                departmentRepository.save(entity);

        return departmentMapper.toResponseDTO(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {

        DepartmentEntity entity = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with id : " + id
                        ));

        departmentRepository.delete(entity);
    }
}