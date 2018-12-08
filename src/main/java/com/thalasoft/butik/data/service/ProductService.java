package com.thalasoft.butik.data.service;

import com.thalasoft.butik.data.exception.EntityNotFoundException;
import com.thalasoft.butik.data.jpa.domain.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProductService {

  public Product add(Product product);

  public Product update(Long id, Product product) throws EntityNotFoundException;

  public Product partialUpdate(Long id, Product product) throws EntityNotFoundException;

  public Product delete(Long id) throws EntityNotFoundException;

  public Product findById(Long id) throws EntityNotFoundException;

  public Page<Product> all(Pageable page);

  public Product findByName(String name) throws EntityNotFoundException;

  public Page<Product> search(String searchTerm, Pageable page);

  public void addSortToPageable(Pageable page, Sort sort);

}
