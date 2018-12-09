package com.thalasoft.butik.data.it.jpa;

import static com.thalasoft.butik.data.assertion.OrderAssert.assertThatOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import com.thalasoft.butik.data.it.BaseTest;
import com.thalasoft.butik.data.jpa.domain.EmailAddress;
import com.thalasoft.butik.data.jpa.domain.Order;
import com.thalasoft.butik.data.jpa.repository.OrderRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class OrderRepositoryTest extends BaseTest {

  @Autowired
  OrderRepository orderRepository;

  private Order order0;
  private Order order1;
  private Order order2;

  private LocalDateTime someTimeAgo, aboutNow, soonEnough;

  private Sort sort;

  public OrderRepositoryTest() {
    someTimeAgo = LocalDateTime.now().minusMinutes(10);
    aboutNow = LocalDateTime.now();
    soonEnough = LocalDateTime.now().plusMinutes(10);

    order0 = new Order();
    order0.setOrderRefId(1);
    order0.setEmail(new EmailAddress("peter@gmail.com"));
    order0.setOrderedOn(someTimeAgo);

    order1 = new Order();
    order1.setOrderRefId(2);
    order1.setEmail(new EmailAddress("paul@gmail.com"));
    order1.setOrderedOn(aboutNow);

    order2 = new Order();
    order2.setOrderRefId(3);
    order2.setEmail(new EmailAddress("marie@gmail.com"));
    order2.setOrderedOn(soonEnough);

    sort = Sort.by(Sort.Order.asc("orderRefId"));
  }

  @Before
  public void beforeAnyTest() throws Exception {
    order0 = orderRepository.saveAndFlush(order0);
    order1 = orderRepository.saveAndFlush(order1);
    order2 = orderRepository.saveAndFlush(order2);
  }

  @After
  public void afterAnyTest() {
    orderRepository.delete(order0);
    orderRepository.delete(order1);
    orderRepository.delete(order2);
  }

  @Test
  public void testSaveAndRetrieve() {
    assertNotNull(order2.getId());
    Optional<Order> loadedOrder = orderRepository.findById(order2.getId());
    assertThatOrder(loadedOrder.get()).hasAnIdNotNull().hasEmail(order2.getEmail())
        .hasOrderRefId(order2.getOrderRefId()).isSameAs(order2);
    assertEquals(order2.getOrderProducts().size(), loadedOrder.get().getOrderProducts().size());
  }

  @Test
  public void testDeleteById() {
    Optional<Order> loadedOrder = orderRepository.findById(order0.getId());
    assertTrue(loadedOrder.isPresent());
    orderRepository.deleteById(order0.getId());
    loadedOrder = orderRepository.findById(order0.getId());
    assertFalse(loadedOrder.isPresent());
  }

  @Test
  public void testDeleteByOrderId() {
    Optional<Order> loadedOrder = orderRepository.findById(order0.getId());
    assertTrue(loadedOrder.isPresent());
    orderRepository.deleteByOrderId(order0.getId());
    loadedOrder = orderRepository.findById(order0.getId());
    assertFalse(loadedOrder.isPresent());
  }

  @Test
  public void testFindByEmail() {
    Optional<Order> loadedOrder = orderRepository.findByEmail(order0.getEmail());
    assertThatOrder(loadedOrder.get()).hasEmail(order0.getEmail());
  }

  @Test
  public void testFindAll() {
    assertEquals(3, orderRepository.count());
    Pageable pageRequest = PageRequest.of(0, 2, sort);
    Page<Order> orders = orderRepository.all(pageRequest);
    assertEquals(2, orders.getContent().size());
    assertThatOrder(orders.getContent().get(0)).hasEmail(order0.getEmail());
    pageRequest = PageRequest.of(1, 2, sort);
    orders = orderRepository.all(pageRequest);
    assertEquals(1, orders.getContent().size());
  }

  @Test
  public void testFindAllBetween() {
    Pageable pageRequest = PageRequest.of(0, 10, sort);
    Page<Order> orders = orderRepository.findAllByOrderedOnBetween(someTimeAgo.minusMinutes(1), aboutNow.plusMinutes(1), pageRequest);
    assertEquals(2, orders.getContent().size());
    orders = orderRepository.findAllByOrderedOnBetween(aboutNow.minusMinutes(1), soonEnough.plusMinutes(1), pageRequest);
    assertEquals(2, orders.getContent().size());
  }

}
