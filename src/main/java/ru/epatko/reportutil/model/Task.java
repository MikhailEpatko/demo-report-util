package ru.epatko.reportutil.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class Task implements Serializable {

    private UUID id;
    private String owner;
    private List<String> questionIds;
    private String reportFormat;
    private String cronExpression;
    private LocalDateTime sendingDate;
    private String[] emails;
}
