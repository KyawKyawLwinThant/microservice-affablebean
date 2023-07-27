package com.example.affablebeanui.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Category {

    private int id;
    private String name;
    @JsonIgnore
    private List<Product> products=
            new ArrayList<>();
}
