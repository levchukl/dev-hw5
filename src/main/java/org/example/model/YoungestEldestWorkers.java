package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data @AllArgsConstructor
public class YoungestEldestWorkers {
    private String type;
    private String name;
    private LocalDate birthday;
}
