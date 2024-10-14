package com.reactive.SpringReactiveCRUD.controller;

import com.reactive.SpringReactiveCRUD.entity.Product;
import com.reactive.SpringReactiveCRUD.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRepo productRepo;

    @GetMapping("/products")
    public Flux<Product> getAllProduct(){
        return productRepo.findAll();
    }

    @GetMapping("/product/{id}")
    public Mono<Product> getProductById(@PathVariable int id){
        return productRepo.findById(id);
    }

    @PostMapping("/insert")
    public Mono<Product> insertProduct(@RequestBody Product product){
        return productRepo.save(product);
    }

    @PutMapping("/update/{id}")
    public Mono<Product> updateProduct(@RequestBody Product product, @PathVariable int id){
        return productRepo.findById(id)
                .map(
                        (c) -> {
                            c.setName(product.getName());
                            c.setPrice(product.getPrice());
                            return c;
                        }
                )
                .flatMap(c -> productRepo.save(c));
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable int id){
        return productRepo.deleteById(id);
    }
}
