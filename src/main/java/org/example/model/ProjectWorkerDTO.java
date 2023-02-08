package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ProjectWorkerDTO {
    private int project_id;
    private int worker_id;
}
