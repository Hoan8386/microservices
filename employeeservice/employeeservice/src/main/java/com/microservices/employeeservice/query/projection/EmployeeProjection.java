package com.microservices.employeeservice.query.projection;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microservices.employeeservice.command.data.Employee;
import com.microservices.employeeservice.command.data.EmployeeRepository;
import com.microservices.employeeservice.query.model.EmployeeResponseModel;
import com.microservices.employeeservice.query.queries.GetAllEmployeeQuery;
import com.microservices.employeeservice.query.queries.GetDetailEmployee;

@Component
public class EmployeeProjection {
    @Autowired
    EmployeeRepository employeeRepository;

    @QueryHandler
    public List<EmployeeResponseModel> handle(GetAllEmployeeQuery query) {
        List<Employee> listEmployee = employeeRepository.findAllByIsDisciplined(query.getIsDisciplined());
        return listEmployee.stream().map(employee -> {
            EmployeeResponseModel employeeResponseModel = new EmployeeResponseModel();
            BeanUtils.copyProperties(employee, employeeResponseModel);
            return employeeResponseModel;
        }).toList();
    }

    @QueryHandler
    public EmployeeResponseModel handle(GetDetailEmployee query) throws Exception {
        Employee employee = employeeRepository.findById(query.getId())
                .orElseThrow(() -> new Exception("employee not found"));
        EmployeeResponseModel model = new EmployeeResponseModel();
        BeanUtils.copyProperties(employee, model);
        return model;
    }
}
