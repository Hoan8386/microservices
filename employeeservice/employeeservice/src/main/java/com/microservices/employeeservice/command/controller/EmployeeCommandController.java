package com.microservices.employeeservice.command.controller;

import org.springframework.web.bind.annotation.RestController;

import com.microservices.employeeservice.command.command.CreateEmployeeCommand;
import com.microservices.employeeservice.command.command.DeleteEmployeeCommand;
import com.microservices.employeeservice.command.command.UpdateEmployeeCommand;
import com.microservices.employeeservice.command.model.EmployeeCreateModel;
import com.microservices.employeeservice.command.model.UpdateEmployeeModel;

import jakarta.validation.Valid;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String addEmployee(@Valid @RequestBody EmployeeCreateModel model) {
        CreateEmployeeCommand command = new CreateEmployeeCommand(UUID.randomUUID().toString(),
                model.getFirstName(),
                model.getLastName(), model.getKin(), false);

        return commandGateway.sendAndWait(command);
    }

    @PutMapping("/{employeeId}")
    public String updateEmployee(@Valid @RequestBody UpdateEmployeeModel model, @PathVariable String employeeId) {
        UpdateEmployeeCommand command = new UpdateEmployeeCommand(employeeId, model.getFirstName(), model.getLastName(),
                model.getKin(), model.getIsDisciplined());
        return commandGateway.sendAndWait(command);
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId) {
        DeleteEmployeeCommand command = new DeleteEmployeeCommand(employeeId);
        return commandGateway.sendAndWait(command);
    }
}
