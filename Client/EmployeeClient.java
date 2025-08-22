package com.practices.demo.Client;

import com.practices.demo.dto.EmployeeDto;

import java.util.List;

public interface EmployeeClient {
//    List<EmployeeDto> getAllEmployees();
    EmployeeDto getEmployeeById(int id);
}
