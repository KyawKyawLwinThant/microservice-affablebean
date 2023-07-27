package com.example.transportationservice.service;

import com.example.transportationservice.entity.CartItem;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CartItemService {
    private final HttpGraphQlClient graphQlClient;
    public CartItemService(){
        WebClient client= WebClient.builder()
                .baseUrl("http://localhost:8080/graphql")
                .build();
        graphQlClient=HttpGraphQlClient.builder(client).build();
    }

    public Mono<List<CartItem>> getAllCartItems(){
        String document= """
                query{
                    cartItems{
                        id
                        name
                        price
                        description
                        quantity
                        lastUpdate
                    }
                }
                """;
        return graphQlClient.document(document)
                .retrieve("cartItems")
                .toEntityList(CartItem.class);
    }

}









