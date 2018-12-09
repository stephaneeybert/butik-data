package com.thalasoft.butik.data.jpa.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "shop_order")
@SequenceGenerator(name = "id_generator", sequenceName = "shop_order_id_seq", allocationSize = 10)
public class Order extends AbstractEntity {

  @Column(name = "order_ref_id", nullable = false)
  private Integer orderRefId;

  @Column(nullable = false, unique = true)
  private EmailAddress email;

  @Column(nullable = false)
  private LocalDateTime orderedOn;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "order")
  private Set<OrderProduct> orderProducts = new HashSet<>();

  public void addProduct(Product product) {
    boolean present = false;
    for (OrderProduct orderProduct : this.orderProducts) {
      if (orderProduct.getProduct().equals(product)) {
        present = true;
      }
    }
    if (!present) {
      OrderProduct orderProduct = new OrderProduct();
      orderProduct.setProduct(product);
      orderProduct.setPrice(product.getPrice());
      orderProduct.setOrder(this);
      this.orderProducts.add(orderProduct);
    }
  }

  public void removeProduct(Product product) {
    // Deleting within a loop trigers a concurrent exception
    OrderProduct orderProductToRemove = null;
    for (OrderProduct orderProduct : this.orderProducts) {
      if (orderProduct.getProduct().equals(product)) {
        orderProductToRemove = orderProduct;
      }
    }
    if (null != orderProductToRemove) {
      orderProductToRemove.setProduct(null);
      this.orderProducts.remove(orderProductToRemove);
    }
  }

}
