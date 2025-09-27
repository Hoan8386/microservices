package com.microservices.employeeservice.command.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreateModel {

    @NotBlank(message = "First name is not empty")
    private String firstName;

    @NotBlank(message = "First name is not empty")
    private String lastName;

    @NotBlank(message = "First name is not empty")
    private String kin;
}
