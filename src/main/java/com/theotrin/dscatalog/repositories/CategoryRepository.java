package com.theotrin.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theotrin.dscatalog.entities.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
