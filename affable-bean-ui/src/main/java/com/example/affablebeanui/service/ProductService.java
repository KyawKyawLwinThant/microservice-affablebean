package com.example.affablebeanui.service;

import com.example.affablebeanui.entity.Product;
import com.example.affablebeanui.entity.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    public static final int DELIVERY_CHANGE = 3;
    private final CartService cartService;





    private List<Product> products;
    private RestTemplate restTemplate=new RestTemplate();
    record TransferData(String to_email,String from_email,double amount){}
    public ResponseEntity transfer(String to_email,
                                   String from_email,
                                   double amount){
         var data = new TransferData(to_email,from_email,amount+ DELIVERY_CHANGE);
         return  restTemplate.postForEntity("http://localhost:8090/account/transfer",data,String.class);
    }
    public ResponseEntity saveCartItem(){
        return restTemplate.getForEntity("http://localhost:9000/transport/cart/save",
                String.class);
    }

    public List<Product> findProductByCategory(int categoryId){
        return products.stream()
                .filter( p -> p.getCategory().getId() == categoryId)
                .collect(Collectors.toList());
    }
    public Product purchaseProduct(int id){
        Product product=findProduct(id);
        cartService.addToCart(product);
        return product;
    }
    private Product findProduct(int id){
        return products.stream()
                .filter(p -> p.getId() == id)
                .findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public ProductService(final CartService cartService){
        this.cartService=cartService;
        var productsResponseEntity=restTemplate
                .getForEntity("http://localhost:8095/backend/products"
                        ,Products.class);
        if(productsResponseEntity.getStatusCode().is2xxSuccessful()){
            products=productsResponseEntity.getBody().getProducts();
            return;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    public List<Product> showAllProducts(){
        return products;
    }


}
