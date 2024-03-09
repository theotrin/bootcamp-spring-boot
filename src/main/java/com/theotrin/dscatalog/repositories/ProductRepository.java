package com.theotrin.dscatalog.repositories;

import com.theotrin.dscatalog.entities.Category;
import com.theotrin.dscatalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
