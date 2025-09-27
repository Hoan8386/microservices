package com.microservices.bookservice.command.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestModel {
    private String id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 30, message = "Name is between 2 and 30 characters")
    private String name;

    @NotBlank(message = "Name is mandatory")
    @Email
    private String author;

    private Boolean isReady;
}