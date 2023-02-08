package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data @AllArgsConstructor
public class WorkerDTO {
    private int id;
    private String name;
    private LocalDate birthday;
    private WorkerLevel level;
    private int salary;
}
