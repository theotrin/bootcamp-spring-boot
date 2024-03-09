package com.theotrin.dscatalog.services;

import com.theotrin.dscatalog.dto.ProductDTO;
import com.theotrin.dscatalog.entities.Product;
import com.theotrin.dscatalog.repositories.ProductRepository;
import com.theotrin.dscatalog.services.exceptions.DatabaseException;
import com.theotrin.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository repository;
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {

        Page<Product> list = repository.findAll(pageRequest);

        return list.map(x -> new ProductDTO(x));

    };


    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj =  repository.findById(id);

        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

        return new ProductDTO(entity,entity.getCategories());
    }
    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
       // entity.setName(dto.getName());
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id,ProductDTO dto) {
        try {
            Product entity = repository.getOne(id);
          //  entity.setName(dto.getName());
            entity = repository.save(entity);
            return new ProductDTO(entity);
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

