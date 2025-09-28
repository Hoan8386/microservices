package com.microservices.employeeservice.query.controller;

import org.springframework.web.bind.annotation.RestController;

import com.microservices.employeeservice.query.model.EmployeeResponseModel;
import com.microservices.employeeservice.query.queries.GetAllEmployeeQuery;
import com.microservices.employeeservice.query.queries.GetDetailEmployee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

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
@Tag(name = "Employee Query")
public class EmployeeController {
        @Autowired
        private QueryGateway queryGateway;

        @Operation(summary = "get list employees", description = "get endpoint for employee with filter ", responses = {
                        @ApiResponse(description = "success", responseCode = "200"),

                        @ApiResponse(responseCode = "401", description = "Unauthorized / Invalid Token")
        })
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
                                .query(new GetDetailEmployee(employeeId),
                                                ResponseTypes.instanceOf(EmployeeResponseModel.class))
                                .join();
        }
}
