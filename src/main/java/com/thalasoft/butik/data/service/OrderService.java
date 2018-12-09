package com.thalasoft.butik.data.service;

import java.time.LocalDateTime;

import com.thalasoft.butik.data.exception.EntityNotFoundException;
import com.thalasoft.butik.data.jpa.domain.Order;
import com.thalasoft.butik.data.jpa.domain.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrderService {

  public Order add(Order order);

  public Order update(Long id, Order order) throws EntityNotFoundException;

  public Order partialUpdate(Long id, Order order) throws EntityNotFoundException;

  public Order delete(Long id) throws EntityNotFoundException;

  public Order addProduct(Order order, Product product) throws EntityNotFoundException;

  public Order removeProduct(Order order, Product product) throws EntityNotFoundException;

  public Order findById(Long id) throws EntityNotFoundException;

  public Page<Order> all(Pageable page);

  public Page<Order> findAllByOrderedOnBetween(LocalDateTime openingDateTime, LocalDateTime closingDateTime, Pageable page);

  public Order findByOrderRefId(Long orderRefId) throws EntityNotFoundException;

  public Order findByEmail(String email) throws EntityNotFoundException;

  public void addSortToPageable(Pageable page, Sort sort);

}
