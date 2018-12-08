package com.thalasoft.butik.data.service;

import java.util.Optional;

import javax.annotation.Resource;

import com.thalasoft.butik.data.exception.EntityAlreadyExistsException;
import com.thalasoft.butik.data.exception.EntityNotFoundException;
import com.thalasoft.butik.data.jpa.domain.Product;
import com.thalasoft.butik.data.jpa.repository.ProductRepository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

  @Resource
  private ProductRepository productRepository;

  @Override
  public Page<Product> all(Pageable page) {
    Page<Product> products = productRepository.all(page);
    if (products.getNumberOfElements() > 0) {
      return products;
    } else {
      throw new EntityNotFoundException();
    }
  }

  @Override
  public Product findByName(String name) {
    Optional<Product> product = productRepository.findByName(name);
    if (product.isPresent()) {
      return product.get();
    } else {
      throw new EntityNotFoundException();
    }
  }

  @Override
  public Page<Product> search(String searchTerm, Pageable page) {
    Page<Product> products = productRepository.search(searchTerm, page);
    if (products.getNumberOfElements() > 0) {
      return products;
    } else {
      throw new EntityNotFoundException();
    }
  }

  @Override
  public Product findById(Long id) {
    Optional<Product> product = productRepository.findById(id);
    if (product.isPresent()) {
      return product.get();
    } else {
      throw new EntityNotFoundException();
    }
  }

  @Modifying
  @Transactional
  @Override
  public Product add(Product product) {
    try {
      findByName(product.getName().toString());
      throw new EntityAlreadyExistsException();
    } catch (EntityNotFoundException e) {
      // Save the returned id into the entity
      product = productRepository.saveAndFlush(product);
      return product;
    }
  }

  @Modifying
  @Transactional
  @Override
  public Product update(Long existingProductId, Product modifiedProduct) {
    Product existingProduct = findById(existingProductId);
    if (existingProduct == null) {
      throw new EntityNotFoundException();
    } else {
      existingProduct.setName(modifiedProduct.getName());
      existingProduct.setPrice(modifiedProduct.getPrice());
      // Save the returned id into the entity
      existingProduct = productRepository.saveAndFlush(existingProduct);
      return existingProduct;
    }
  }

  @Modifying
  @Transactional
  @Override
  public Product partialUpdate(Long existingProductId, Product modifiedProduct) {
    Product existingProduct = findById(existingProductId);
    if (existingProduct == null) {
      throw new EntityNotFoundException();
    } else {
      if (StringUtils.isNotEmpty(modifiedProduct.getName())) {
        existingProduct.setName(modifiedProduct.getName());
      }
      if (StringUtils.isNotEmpty(modifiedProduct.getPrice())) {
        existingProduct.setPrice(modifiedProduct.getPrice());
      }
      // Save the returned id into the entity
      existingProduct = productRepository.saveAndFlush(existingProduct);
      return existingProduct;
    }
  }

  @Modifying
  @Transactional
  @Override
  public Product delete(Long id) {
    Product product = findById(id);
    if (product == null) {
      throw new EntityNotFoundException();
    } else {
      productRepository.delete(product);
      return product;
    }
  }

  @Override
  public void addSortToPageable(Pageable page, Sort sort) {
    page.getSort().and(sort);
  }

}
