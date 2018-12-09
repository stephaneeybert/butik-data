package com.thalasoft.butik.data.it.service;

import static com.thalasoft.butik.data.assertion.OrderAssert.assertThatOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.thalasoft.butik.data.exception.EntityNotFoundException;
import com.thalasoft.butik.data.it.BaseTest;
import com.thalasoft.butik.data.jpa.domain.EmailAddress;
import com.thalasoft.butik.data.jpa.domain.Order;
import com.thalasoft.butik.data.jpa.domain.Product;
import com.thalasoft.butik.data.service.OrderService;
import com.thalasoft.butik.data.service.ProductService;
import com.thalasoft.toolbox.utils.CommonTools;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class OrderServiceTest extends BaseTest {

  @Autowired
  private OrderService orderService;

  @Autowired
  private ProductService productService;

  private Order order0;
  private List<Product> manyProducts;

  private LocalDateTime someTimeAgo, aboutNow, soonEnough;

  private Sort sort;

  public OrderServiceTest() {
    someTimeAgo = LocalDateTime.now().minusMinutes(10);
    aboutNow = LocalDateTime.now();
    soonEnough = LocalDateTime.now().plusMinutes(10);

    sort = Sort.by(Sort.Order.asc("orderRefId"));
  }

  @Before
  public void beforeAnyTest() throws Exception {
    manyProducts = new ArrayList<Product>();
    for (int i = 0; i < 40; i++) {
      String index = CommonTools.formatSortableStringNumber(i, 3);
      Product oneProduct = new Product();
      oneProduct.setName("product" + index);
      oneProduct.setPrice("11");
      oneProduct = productService.add(oneProduct);
      manyProducts.add(oneProduct);
    }

    order0 = new Order();
    order0.setOrderRefId(1);
    order0.setEmail(new EmailAddress("peter@gmail.com"));
    order0.setOrderedOn(aboutNow);
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

  @Test
  public void testFindAllBetween() {
    Pageable pageRequest = PageRequest.of(0, 10, sort);
    Page<Order> orders = orderService.findAllByOrderedOnBetween(aboutNow.minusMinutes(1), aboutNow.plusMinutes(1),
        pageRequest);
    assertEquals(1, orders.getContent().size());
    try {
      orders = orderService.findAllByOrderedOnBetween(aboutNow.plusMinutes(1), soonEnough.plusMinutes(1), pageRequest);
    } catch (EntityNotFoundException e) {
    }
  }

}