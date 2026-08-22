package com.hrms.department.entity;

import com.hrms.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_code", nullable = false, unique = true, length = 20)
    private String departmentCode;

    @Column(name = "department_name", nullable = false, length = 100)
    private String departmentName;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Boolean status;
}