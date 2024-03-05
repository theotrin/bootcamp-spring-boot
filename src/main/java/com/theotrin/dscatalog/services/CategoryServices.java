package com.theotrin.dscatalog.services;

import com.theotrin.dscatalog.entities.Category;
import com.theotrin.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServices {

    @Autowired
    private CategoryRepository repository;
    @Transactional(readOnly = true)
    public List<Category> findAll() {

        return repository.findAll();
    };

}
