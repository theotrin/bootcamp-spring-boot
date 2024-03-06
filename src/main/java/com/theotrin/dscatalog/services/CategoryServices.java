package com.theotrin.dscatalog.services;

import com.theotrin.dscatalog.dto.CategoryDTO;
import com.theotrin.dscatalog.entities.Category;
import com.theotrin.dscatalog.repositories.CategoryRepository;
import com.theotrin.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
    @Service
    public class CategoryServices {

        @Autowired
        private CategoryRepository repository;
        @Transactional(readOnly = true)
        public List<CategoryDTO> findAll() {

            List<Category> list = repository.findAll();

            return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());

        };

        @Transactional(readOnly = true)
        public CategoryDTO findById(Long id) {
            Optional<Category> obj =  repository.findById(id);

            Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

            return new CategoryDTO(entity);
        }
        @Transactional
        public CategoryDTO insert(CategoryDTO dto) {
            Category entity = new Category();
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new CategoryDTO(entity);
        }

        @Transactional
        public CategoryDTO update(Long id,CategoryDTO dto) {
            try {
                Category entity = repository.getOne(id);
                entity.setName(dto.getName());
                entity = repository.save(entity);
                return new CategoryDTO(entity);
            }
            catch (EntityNotFoundException e ) {
                throw new ResourceNotFoundException("id not found " + id);
            }
        }
    }

