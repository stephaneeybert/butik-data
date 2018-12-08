package com.thalasoft.butik.data.jpa.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import com.thalasoft.butik.data.exception.EntityNotFoundException;
import com.thalasoft.butik.data.jpa.domain.Order;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

  @Autowired
  private OrderRepository OrderRepository;

  @Override
  @Transactional
  public Order deleteByOrderId(Long id) {
    Optional<Order> entity = OrderRepository.findById(id);
    if (entity.isPresent()) {
      OrderRepository.delete(entity.get());
    } else {
      throw new EntityNotFoundException("The Order entity could not be found and was not deleted");
    }
    return entity.get();
  }

}
