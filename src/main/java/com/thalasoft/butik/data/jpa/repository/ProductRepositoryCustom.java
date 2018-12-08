package com.thalasoft.butik.data.jpa.repository;

import com.thalasoft.butik.data.jpa.domain.Product;

public interface ProductRepositoryCustom {

  public Product deleteByProductId(Long id);

}
