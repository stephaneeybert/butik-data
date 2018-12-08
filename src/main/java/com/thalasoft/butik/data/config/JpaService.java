package com.thalasoft.butik.data.config;

import com.thalasoft.butik.data.service.OrderServiceImpl;
import com.thalasoft.butik.data.service.ProductServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaService {

  @Bean
  public ProductServiceImpl ProductService() {
    return new ProductServiceImpl();
  }

  @Bean
  public OrderServiceImpl OrderService() {
    return new OrderServiceImpl();
  }

}
