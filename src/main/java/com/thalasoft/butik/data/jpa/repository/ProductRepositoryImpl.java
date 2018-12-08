package com.thalasoft.butik.data.jpa.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import com.thalasoft.butik.data.exception.EntityNotFoundException;
import com.thalasoft.butik.data.jpa.domain.Product;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

  @Autowired
  private ProductRepository ProductRepository;

  @Override
  @Transactional
  public Product deleteByProductId(Long id) {
    Optional<Product> entity = ProductRepository.findById(id);
    if (entity.isPresent()) {
      ProductRepository.delete(entity.get());
    } else {
      throw new EntityNotFoundException("The product entity could not be found and was not deleted");
    }
    return entity.get();
  }

}
