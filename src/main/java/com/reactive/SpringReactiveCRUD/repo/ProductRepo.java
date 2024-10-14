package com.reactive.SpringReactiveCRUD.repo;

import com.reactive.SpringReactiveCRUD.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepo extends ReactiveCrudRepository<Product, Integer> {
}
