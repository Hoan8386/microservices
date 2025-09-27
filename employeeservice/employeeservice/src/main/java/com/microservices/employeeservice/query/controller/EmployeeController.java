package com.microservices.employeeservice.query.controller;

import org.springframework.web.bind.annotation.RestController;

import com.microservices.employeeservice.query.model.EmployeeResponseModel;
import com.microservices.employeeservice.query.queries.GetAllEmployeeQuery;
import com.microservices.employeeservice.query.queries.GetDetailEmployee;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping
    public List<EmployeeResponseModel> getAllEmployee(
            @RequestParam(required = false, defaultValue = "false") Boolean isDisciplined) {
        return queryGateway
                .query(new GetAllEmployeeQuery(isDisciplined),
                        ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class))
                .join();
    }

    @GetMapping("/{employeeId}")
    public EmployeeResponseModel getDetail(@PathVariable String employeeId) {
        return queryGateway
                .query(new GetDetailEmployee(employeeId), ResponseTypes.instanceOf(EmployeeResponseModel.class)).join();
    }
}
