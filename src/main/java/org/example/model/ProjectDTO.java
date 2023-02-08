package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data @AllArgsConstructor
public class ProjectDTO {
    private int id;
    private int client_id;
    private LocalDate start_date;
    private LocalDate finish_date;
}
