package com.hrms.department.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentRequestDTO {

    @Schema(
            description = "Department name",
            example = "Finance"
    )
    @NotBlank(message = "Department name is required")
    private String departmentName;

    @Schema(
            description = "Short description of the department",
            example = "Handles Financial Operations"
    )
    private String description;

    @Schema(
            description = "Department status (true = Active, false = Inactive)",
            example = "true"
    )
    @NotNull(message = "Status is required")
    private Boolean status;
}