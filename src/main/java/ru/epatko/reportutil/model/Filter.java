package ru.epatko.reportutil.model;


import lombok.Data;

@Data
public class Filter {

    private String type;
    private String target;
    private String templateTag;
}
