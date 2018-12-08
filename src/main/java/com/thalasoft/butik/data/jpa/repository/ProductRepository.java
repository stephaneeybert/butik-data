package com.thalasoft.butik.data.jpa.repository;

import java.util.Optional;

import com.thalasoft.butik.data.jpa.domain.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

  @Query("SELECT p FROM Product p")
  public Page<Product> all(Pageable page);

  public Optional<Product> findByName(String name);

  @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
  public Page<Product> search(@Param("searchTerm") String searchTerm, Pageable page);

  @Modifying
  @Query("UPDATE Product SET price = :price WHERE id = :id")
  public void updatePrice(@Param("price") String price, @Param("id") Long id);

}
