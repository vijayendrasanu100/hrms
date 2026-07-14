package com.hrms.department.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentResponseDTO {

    @Schema(
            description = "Unique department id",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Unique department code",
            example = "FIN"
    )
    private String departmentCode;

    @Schema(
            description = "Department name",
            example = "Finance"
    )
    private String departmentName;

    @Schema(
            description = "Department description",
            example = "Handles Financial Operations"
    )
    private String description;

    @Schema(
            description = "Department status",
            example = "true"
    )
    private Boolean status;

    @Schema(
            description = "Department creation timestamp",
            example = "2026-07-11T10:30:45"
    )
    private LocalDateTime createdAt;

    @Schema(
            description = "Department last updated timestamp",
            example = "2026-07-12T15:45:20"
    )
    private LocalDateTime updatedAt;
}