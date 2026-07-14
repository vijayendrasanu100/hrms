package com.hrms.department.controller;

import com.hrms.common.response.ApiResponse;
import com.hrms.department.dto.DepartmentRequestDTO;
import com.hrms.department.dto.DepartmentResponseDTO;
import com.hrms.department.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
@Tag(
        name = "Department API",
        description = "Department Management REST APIs"
)
public class DepartmentController {

    private final DepartmentService departmentService;

    @Operation(
            summary = "Create Department",
            description = "Creates a new department in the HRMS system."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "Department created successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Validation failed"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "409",
                    description = "Department code already exists"
            )
    })

    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentResponseDTO>> createDepartment(
            @Valid @RequestBody DepartmentRequestDTO requestDTO) {

        DepartmentResponseDTO responseDTO =
                departmentService.createDepartment(requestDTO);

        ApiResponse<DepartmentResponseDTO> response =
                ApiResponse.<DepartmentResponseDTO>builder()
                        .success(true)
                        .message("Department created successfully")
                        .data(responseDTO)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @Operation(
            summary = "Get All Departments",
            description = "Fetches all departments with pagination support."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Departments fetched successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid pagination request"
            )
    })
    @GetMapping
    public ResponseEntity<ApiResponse<Page<DepartmentResponseDTO>>> getAllDepartments(

            @Parameter(
                    description = "Pagination parameters (page, size, sort)"
            )
            @ParameterObject
            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "id",
                    direction = Sort.Direction.ASC
            )
            Pageable pageable) {

        Page<DepartmentResponseDTO> departments =
                departmentService.getAllDepartments(pageable);

        ApiResponse<Page<DepartmentResponseDTO>> response =
                ApiResponse.<Page<DepartmentResponseDTO>>builder()
                        .success(true)
                        .message("Departments fetched successfully")
                        .data(departments)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Get Department By Id",
            description = "Fetches a department using its unique id."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Department fetched successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Department not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponseDTO>> getDepartmentById(
            @PathVariable Long id) {

        DepartmentResponseDTO responseDTO =
                departmentService.getDepartmentById(id);

        ApiResponse<DepartmentResponseDTO> response =
                ApiResponse.<DepartmentResponseDTO>builder()
                        .success(true)
                        .message("Department fetched successfully")
                        .data(responseDTO)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Update Department",
            description = "Updates an existing department using its unique id."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Department updated successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Validation failed"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Department not found"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "409",
                    description = "Department code already exists"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponseDTO>> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequestDTO requestDTO) {

        DepartmentResponseDTO responseDTO =
                departmentService.updateDepartment(id, requestDTO);

        ApiResponse<DepartmentResponseDTO> response =
                ApiResponse.<DepartmentResponseDTO>builder()
                        .success(true)
                        .message("Department updated successfully")
                        .data(responseDTO)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Delete Department",
            description = "Deletes a department using its unique id."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Department deleted successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Department not found"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteDepartment(@PathVariable Long id) {

        departmentService.deleteDepartment(id);

        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .message("Department deleted successfully")
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }
}