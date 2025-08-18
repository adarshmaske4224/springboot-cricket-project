package com.example.cricket.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class CrickDto {             //plain old java object class (pojo)



    private int id;


    @NotBlank(message = "Enter your name")
    @Size(min = 2, max = 50, message = "name must be between 2 to 50 characters")
    private String name;

    @Min(value=15, message="Age must be greater than 14")
    @Max(value=49, message = "Age must be less than 50")
    private int age;

    @NotBlank(message = "Role is Required (e.g., Batsman, Bowler)")
    private String role;

    @NotBlank(message = "team name cannot be blank")
    @Size(min=2, max = 30, message = "team name must be between 2 to 30 characters")
    private String team;

    @PositiveOrZero(message = "Total Runs cannot be negative")
    private int totalRuns;

    @PositiveOrZero(message = "wickets cannot be negative")
    private int wickets;


}
