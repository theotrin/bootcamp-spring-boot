package com.theotrin.dscatalog.services;

import com.theotrin.dscatalog.dto.CategoryDTO;
import com.theotrin.dscatalog.entities.Category;
import com.theotrin.dscatalog.repositories.CategoryRepository;
import com.theotrin.dscatalog.services.exceptions.DatabaseException;
import com.theotrin.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        public Page<CategoryDTO> findAllPaged(PageRequest pageRequest) {

            Page<Category> list = repository.findAll(pageRequest);

            return list.map(x -> new CategoryDTO(x));

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
                throw new ResourceNotFoundException("Id not found " + id);
            }
        }

        public void delete(Long id) {
           try {
               if (repository.existsById(id)) {
                   repository.deleteById(id);
               }
               throw new ResourceNotFoundException("Id not found");
           }
            catch (EntityNotFoundException e) {
               throw new ResourceNotFoundException("Id not found");
            }
            catch (DataIntegrityViolationException e){
               throw new DatabaseException("Integrity violation");
            }
           }
    }

