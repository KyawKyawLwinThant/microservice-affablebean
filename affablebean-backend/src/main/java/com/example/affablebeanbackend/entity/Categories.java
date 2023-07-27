package com.example.affablebeanbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Data
@AllArgsConstructor
public class Categories {

    private List<Category> categories;
    public Categories(){}

    public Categories(Spliterator<Category> iterable){
        categories= StreamSupport.stream(iterable,false)
                .collect(Collectors.toList());
    }


}
