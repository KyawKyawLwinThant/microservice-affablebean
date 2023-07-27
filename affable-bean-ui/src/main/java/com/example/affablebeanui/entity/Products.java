package com.example.affablebeanui.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Data
@AllArgsConstructor
public class Products {
    private List<Product> products;
    public Products(){}

    public Products(Spliterator<Product> iterable){
        products= StreamSupport.stream(iterable,false)
                .collect(Collectors.toList());

    }
}
