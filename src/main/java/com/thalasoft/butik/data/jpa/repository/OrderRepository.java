package com.thalasoft.butik.data.jpa.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import com.thalasoft.butik.data.jpa.domain.EmailAddress;
import com.thalasoft.butik.data.jpa.domain.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

  public Optional<Order> findByOrderRefId(long orderRefId);

  public Optional<Order> findByEmail(EmailAddress email);

  @Query("SELECT o FROM Order o")
  public Page<Order> all(Pageable page);

  public Page<Order> findAllByOrderedOnBetween(LocalDateTime openingDateTime, LocalDateTime closingDateTime, Pageable page);

  @Modifying
  @Query("UPDATE Order SET email = :email WHERE id = :id")
  public void updateEmail(@Param("email") String email, @Param("id") Long id);

}
