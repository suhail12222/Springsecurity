package com.practices.demo.Client.impl;

import com.practices.demo.Client.EmployeeClient;
import com.practices.demo.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
@Service
@RequiredArgsConstructor
@ToString
public class EmployeeClientImplemntation implements EmployeeClient {
//    @Value("${employeeService.base.url}")
    @Qualifier("getRestClient")
    private final RestClient restClient;

    @Override
    public EmployeeDto getEmployeeById(int id) {
        EmployeeDto employeeDto=restClient.get()
                .uri("/employees/{id}",id)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
        return employeeDto;
    }

//    @Override
//    public List<EmployeeDto> getAllEmployees() {
//
//        List<EmployeeDto> employeeDtos=restClient.get().uri("/employees").retrieve().body(new ParameterizedTypeReference<>() {});
//                return employeeDtos;
//
//    }
}
