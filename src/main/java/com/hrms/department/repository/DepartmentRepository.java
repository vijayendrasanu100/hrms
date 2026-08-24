package com.hrms.department.repository;

import com.hrms.department.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

    Optional<DepartmentEntity> findByDepartmentCode(String departmentCode);

    Optional<DepartmentEntity> findByDepartmentName(String departmentName);

    boolean existsByDepartmentCode(String departmentCode);

    @Query("""
           SELECT COALESCE(MAX(
               CAST(SUBSTRING(d.departmentCode, LENGTH(d.departmentCode) - 2, 3) AS integer)
           ), 0)
           FROM DepartmentEntity d
           WHERE LENGTH(d.departmentCode) >= 3
           """)
    Integer findMaxDepartmentNumber();
}