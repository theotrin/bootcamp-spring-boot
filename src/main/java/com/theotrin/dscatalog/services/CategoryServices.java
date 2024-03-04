package com.theotrin.dscatalog.services;

import com.theotrin.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.theotrin.dscatalog.entities.Category;

import java.util.List;

@Service
public class CategoryServices {

    @Autowired
    private CategoryRepository repository;
    public List<Category> findAll() {
        return repository.findAll();
    };

}
