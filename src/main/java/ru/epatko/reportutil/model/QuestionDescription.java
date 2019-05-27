package ru.epatko.reportutil.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionDescription {

    private String questionId;
    private String result;
    private String description;
    private List<Filter> filters = new ArrayList<>();
}
