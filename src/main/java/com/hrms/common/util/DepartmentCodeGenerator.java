package com.hrms.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DepartmentCodeGenerator {

    private DepartmentCodeGenerator() {
        // Utility class
    }

    public static String generateCode(String departmentName, int number) {

        String prefix = departmentName
                .trim()
                .substring(0, 3)
                .toUpperCase();

        String monthYear = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("MMyyyy"));

        return prefix + monthYear + String.format("%03d", number);
    }
}