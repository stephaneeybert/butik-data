package com.thalasoft.butik.data.jpa.repository;

import com.thalasoft.butik.data.jpa.domain.Order;

public interface OrderRepositoryCustom {

  public Order deleteByOrderId(Long id);

}
