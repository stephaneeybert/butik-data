package com.thalasoft.butik.data.it.jpa;

import static com.thalasoft.butik.data.assertion.ProductAssert.assertThatProduct;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.thalasoft.butik.data.it.BaseTest;
import com.thalasoft.butik.data.jpa.domain.Product;
import com.thalasoft.butik.data.jpa.repository.ProductRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ProductRepositoryTest extends BaseTest {

  @Autowired
  ProductRepository productRepository;

  private Product product0;
  private Product product1;
  private Product product2;
  private Product product3;
  private List<Product> manyProducts;

  private Sort sort;

  public ProductRepositoryTest() {
    product0 = new Product();
    product0.setName("Air fan");
    product0.setPrice("10");

    product1 = new Product();
    product1.setName("Air conditionner");
    product1.setPrice("100");

    product2 = new Product();
    product2.setName("Water cooler");
    product2.setPrice("22");

    product3 = new Product();
    product3.setName("Water boiler");
    product3.setPrice("14");

    manyProducts = new ArrayList<Product>();
    for (int i = 0; i < 39; i++) {
      String index = intToString(i, 3);
      Product oneProduct = new Product();
      oneProduct.setName("product" + index);
      oneProduct.setPrice("11");
      manyProducts.add(oneProduct);
    }

    sort = Sort.by(Sort.Order.asc("name"));
  }

  @Before
  public void beforeAnyTest() throws Exception {
    product0 = productRepository.saveAndFlush(product0);
    product1 = productRepository.saveAndFlush(product1);
    product2 = productRepository.saveAndFlush(product2);
    product3 = productRepository.saveAndFlush(product3);
    for (Product oneProduct : manyProducts) {
      oneProduct = productRepository.saveAndFlush(oneProduct);
    }
  }

  @After
  public void afterAnyTest() {
    productRepository.delete(product0);
    productRepository.delete(product1);
    productRepository.delete(product2);
    productRepository.delete(product3);
    for (Product oneProduct : manyProducts) {
      productRepository.delete(oneProduct);
    }
  }

  @Test
  public void testSaveAndRetrieve() {
    assertNotNull(product2.getId());
    Optional<Product> loadedProduct = productRepository.findById(product2.getId());
    assertThatProduct(loadedProduct.get()).hasAnIdNotNull().hasName(product2.getName()).hasPrice(product2.getPrice())
        .isSameAs(product2);
  }

  @Test
  public void testDeleteById() {
    Optional<Product> loadedProduct = productRepository.findById(product0.getId());
    assertTrue(loadedProduct.isPresent());
    productRepository.deleteById(product0.getId());
    loadedProduct = productRepository.findById(product0.getId());
    assertFalse(loadedProduct.isPresent());
  }

  @Test
  public void testDeleteByProductId() {
    Optional<Product> loadedProduct = productRepository.findById(product0.getId());
    assertTrue(loadedProduct.isPresent());
    productRepository.deleteByProductId(product0.getId());
    loadedProduct = productRepository.findById(product0.getId());
    assertFalse(loadedProduct.isPresent());
  }

  @Test
  public void testFindByName() {
    Optional<Product> loadedProduct = productRepository.findByName(product0.getName());
    assertThatProduct(loadedProduct.get()).hasName(product0.getName());
  }

  @Test
  public void testFindAll() {
    assertEquals(43, productRepository.count());
    Pageable pageRequest = PageRequest.of(0, 10, sort);
    Page<Product> products = productRepository.all(pageRequest);
    assertEquals(10, products.getContent().size());
    assertEquals(product1.getName(), products.getContent().get(0).getName());
    assertEquals(product0.getName(), products.getContent().get(1).getName());
    assertEquals(manyProducts.get(0).getName(), products.getContent().get(2).getName());
    assertEquals(manyProducts.get(1).getName(), products.getContent().get(3).getName());
    pageRequest = PageRequest.of(1, 20, sort);
    products = productRepository.all(pageRequest);
    assertEquals(20, products.getContent().size());
    assertEquals("product018", products.getContent().get(0).getName());
    assertEquals("product037", products.getContent().get(19).getName());
    pageRequest = PageRequest.of(2, 20, sort);
    products = productRepository.all(pageRequest);
    assertEquals(3, products.getContent().size());
    assertEquals(product3.getName(), products.getContent().get(1).getName());
    assertEquals(product2.getName(), products.getContent().get(2).getName());
  }

  @Test
  public void testSearch() {
    Page<Product> products = productRepository.search("product", PageRequest.of(0, 10, sort));
    assertEquals(10, products.getContent().size());
    assertEquals(39, products.getTotalElements());
    assertEquals(4, products.getTotalPages());
    assertEquals("product000", products.getContent().get(0).getName());
    assertEquals("product001", products.getContent().get(1).getName());
    products = productRepository.search("product", PageRequest.of(1, 20, sort));
    assertEquals(19, products.getContent().size());
    assertEquals("product020", products.getContent().get(0).getName());
    assertEquals("product038", products.getContent().get(18).getName());
  }

  @Test
  public void testUpdateProductNameDoesNotUpdatePrice() {
    assertNotNull(product1.getId());
    product1.setName("Filter");
    product1 = productRepository.saveAndFlush(product1);
    Optional<Product> loadedProduct = productRepository.findById(product1.getId());
    assertNotNull(loadedProduct.get().getId());
    assertEquals(product1.getPrice(), loadedProduct.get().getPrice());
  }

}