package ru.epatko.reportutil.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class Question implements Serializable {

    private String id;
    private String name;
    private List<String> legend = new ArrayList<>();
    Set<String> authorizedRoles = new HashSet<>();

    @Override
    public String toString() {
        return name;
    }
}
