package com.thalasoft.butik.data.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Resource;

import com.thalasoft.butik.data.exception.EntityAlreadyExistsException;
import com.thalasoft.butik.data.exception.EntityNotFoundException;
import com.thalasoft.butik.data.jpa.domain.EmailAddress;
import com.thalasoft.butik.data.jpa.domain.Order;
import com.thalasoft.butik.data.jpa.domain.OrderProduct;
import com.thalasoft.butik.data.jpa.domain.Product;
import com.thalasoft.butik.data.jpa.repository.OrderRepository;
import com.thalasoft.butik.data.jpa.repository.ProductRepository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

  @Resource
  private OrderRepository orderRepository;

  @Resource
  private ProductRepository productRepository;
  
  @Override
  public Page<Order> all(Pageable page) {
    Page<Order> orders = orderRepository.all(page);
    if (orders.getNumberOfElements() > 0) {
      return orders;
    } else {
      throw new EntityNotFoundException();
    }
  }

  @Override
  public Page<Order> findAllByOrderedOnBetween(LocalDateTime openingDate, LocalDateTime closingDate, Pageable page) {
    Page<Order> orders = orderRepository.findAllByOrderedOnBetween(openingDate, closingDate, page);
    if (orders.getNumberOfElements() > 0) {
      return orders;
    } else {
      throw new EntityNotFoundException();
    }
  }

  @Override
  public Order findByOrderRefId(Long orderRefId) {
    Optional<Order> order = orderRepository.findByOrderRefId(orderRefId);
    if (order.isPresent()) {
      return order.get();
    } else {
      throw new EntityNotFoundException();
    }
  }

  @Override
  public Order findByEmail(String email) {
    Optional<Order> order = orderRepository.findByEmail(new EmailAddress(email));
    if (order.isPresent()) {
      return order.get();
    } else {
      throw new EntityNotFoundException();
    }
  }

  @Override
  public Order findById(Long id) {
    Optional<Order> order = orderRepository.findById(id);
    if (order.isPresent()) {
      return order.get();
    } else {
      throw new EntityNotFoundException();
    }
  }

  @Modifying
  @Transactional
  @Override
  public Order add(Order order) {
    try {
      findByEmail(order.getEmail().getEmailAddress());
      throw new EntityAlreadyExistsException();
    } catch (EntityNotFoundException e) {
      // Save the returned id into the entity
      order = orderRepository.saveAndFlush(order);
      return order;
    }
  }

  @Modifying
  @Transactional
  @Override
  public Order update(Long existingOrderId, Order modifiedOrder) {
    Order existingOrder = findById(existingOrderId);
    if (existingOrder == null) {
      throw new EntityNotFoundException();
    } else {
      existingOrder.setEmail(new EmailAddress(modifiedOrder.getEmail().getEmailAddress()));
      existingOrder.setOrderRefId(modifiedOrder.getOrderRefId());
      existingOrder.setOrderedOn(modifiedOrder.getOrderedOn());
      existingOrder.setOrderProducts(modifiedOrder.getOrderProducts());
      // Save the returned id into the entity
      existingOrder = orderRepository.saveAndFlush(existingOrder);
      return existingOrder;
    }
  }

  @Modifying
  @Transactional
  @Override
  public Order partialUpdate(Long existingOrderId, Order modifiedOrder) {
    Order existingOrder = findById(existingOrderId);
    if (existingOrder == null) {
      throw new EntityNotFoundException();
    } else {
      if (StringUtils.isNotEmpty(modifiedOrder.getEmail().getEmailAddress())) {
        existingOrder.setEmail(new EmailAddress(modifiedOrder.getEmail().getEmailAddress()));
      }
      if (modifiedOrder.getOrderRefId() > 0) {
        existingOrder.setOrderRefId(modifiedOrder.getOrderRefId());
      }
      if (modifiedOrder.getOrderedOn() != null) {
        existingOrder.setOrderedOn(modifiedOrder.getOrderedOn());
      }
      existingOrder.setOrderProducts(modifiedOrder.getOrderProducts());
      // Save the returned id into the entity
      existingOrder = orderRepository.saveAndFlush(existingOrder);
      return existingOrder;
    }
  }

  @Modifying
  @Transactional
  @Override
  public Order delete(Long id) {
    Order Order = findById(id);
    if (Order == null) {
      throw new EntityNotFoundException();
    } else {
      orderRepository.delete(Order);
      return Order;
    }
  }

  @Modifying
  @Transactional
  @Override
  public Order addProduct(Order order, Product product) {
    Order foundOrder = findById(order.getId());
    if (foundOrder != null) {
      foundOrder.addProduct(product);
      foundOrder = orderRepository.saveAndFlush(foundOrder);
      return foundOrder;
    } else {
      throw new EntityNotFoundException();
    }
  }

  @Modifying
  @Transactional
  @Override
  public Order removeProduct(Order order, Product product) {
    Order foundOrder = findById(order.getId());
    if (foundOrder != null) {
      foundOrder.removeProduct(product);
      foundOrder = orderRepository.saveAndFlush(foundOrder);
      return foundOrder;
    } else {
      throw new EntityNotFoundException();
    }
  }

  @Override
  public void addSortToPageable(Pageable page, Sort sort) {
    page.getSort().and(sort);
  }

}
