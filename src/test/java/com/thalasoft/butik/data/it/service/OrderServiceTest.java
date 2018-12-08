package com.thalasoft.butik.data.it.service;

import static com.thalasoft.butik.data.assertion.OrderAssert.assertThatOrder;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import com.thalasoft.butik.data.it.BaseTest;
import com.thalasoft.butik.data.jpa.domain.EmailAddress;
import com.thalasoft.butik.data.jpa.domain.Order;
import com.thalasoft.butik.data.jpa.domain.Product;
import com.thalasoft.butik.data.service.OrderService;
import com.thalasoft.butik.data.service.ProductService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceTest extends BaseTest {

  @Autowired
  private OrderService orderService;

  @Autowired
  private ProductService productService;

  private Order order0;
  private List<Product> manyProducts;

  public OrderServiceTest() {
  }

  @Before
  public void beforeAnyTest() throws Exception {
    manyProducts = new ArrayList<Product>();
    for (int i = 0; i < 40; i++) {
      String index = intToString(i, 3);
      Product oneProduct = new Product();
      oneProduct.setName("product" + index);
      oneProduct.setPrice("11");
      oneProduct = productService.add(oneProduct);
      manyProducts.add(oneProduct);
    }

    order0 = new Order();
    order0.setOrderRefId(1);
    order0.setEmail(new EmailAddress("peter@gmail.com"));
    order0.addProduct(manyProducts.get(0));
    order0.addProduct(manyProducts.get(1));
    order0.addProduct(manyProducts.get(2));
    orderService.add(order0);
  }

  @After
  public void afterAnyTest() {
    orderService.delete(order0.getId());
    for (Product oneProduct : manyProducts) {
      productService.delete(oneProduct.getId());
    }
  }

  @Test
  public void testAdd() {
    Product product = new Product();
    product.setName("Air conditionner");
    product.setPrice("100");
    product = productService.add(product);
    assertNotNull(product.getId());
  }

  @Test
  public void testAddProduct() {
    order0 = orderService.addProduct(order0, manyProducts.get(0));
    assertThatOrder(order0).hasProduct(manyProducts.get(0));
    order0 = orderService.addProduct(order0, manyProducts.get(1));
    assertThatOrder(order0).hasProduct(manyProducts.get(1));
  }

  @Test
  public void testDelete() {
    order0 = orderService.addProduct(order0, manyProducts.get(0));
    order0 = orderService.addProduct(order0, manyProducts.get(1));
    order0 = orderService.removeProduct(order0, manyProducts.get(1));
    assertThatOrder(order0).hasNotProduct(manyProducts.get(1));
    order0 = orderService.removeProduct(order0, manyProducts.get(0));
    assertThatOrder(order0).hasNotProduct(manyProducts.get(0));
  }

  @Test
  public void testUpdate() {
    EmailAddress email = new EmailAddress("peter@gmail.com");
    Integer orderRefId = 3;
    order0.setEmail(email);
    order0.setOrderRefId(orderRefId);
    order0 = orderService.update(order0.getId(), order0);
    assertThatOrder(order0).hasEmail(email);
    assertThatOrder(order0).hasOrderRefId(orderRefId);
  }

  @Test
  public void testPartialUpdate() {
    EmailAddress email = new EmailAddress("peter@gmail.com");
    order0.setEmail(email);
    Integer originalOrderRefId = order0.getOrderRefId();
    order0.setOrderRefId(0);
    order0 = orderService.partialUpdate(order0.getId(), order0);
    assertThatOrder(order0).hasEmail(email);
    assertThatOrder(order0).hasOrderRefId(originalOrderRefId);
  }

}