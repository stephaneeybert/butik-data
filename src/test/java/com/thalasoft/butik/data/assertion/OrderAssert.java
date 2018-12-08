package com.thalasoft.butik.data.assertion;

import static org.assertj.core.api.Assertions.assertThat;

import com.thalasoft.butik.data.jpa.domain.AbstractEntity;
import com.thalasoft.butik.data.jpa.domain.EmailAddress;
import com.thalasoft.butik.data.jpa.domain.Order;
import com.thalasoft.butik.data.jpa.domain.OrderProduct;
import com.thalasoft.butik.data.jpa.domain.Product;

import org.assertj.core.api.AbstractAssert;

public class OrderAssert extends AbstractAssert<OrderAssert, Order> {

  private OrderAssert(Order actual) {
    super(actual, OrderAssert.class);
  }

  public static OrderAssert assertThatOrder(Order actual) {
    return new OrderAssert(actual);
  }

  public OrderAssert hasId(Long id) {
    isNotNull();
    assertThat(actual.getId()).overridingErrorMessage("Expected the id to be <%s> but was <%s>.", id, actual.getId())
        .isEqualTo(id);
    return this;
  }

  public OrderAssert hasAnIdNotNull() {
    isNotNull();
    assertThat(actual.getId()).overridingErrorMessage("Expected the id to be not null but was null.").isNotNull();
    return this;
  }

  public OrderAssert hasEmail(EmailAddress email) {
    isNotNull();
    assertThat(actual.getEmail().toString())
        .overridingErrorMessage("Expected the email to be <%s> but was <%s>.", email, actual.getEmail())
        .isEqualTo(email.toString());
    return this;
  }

  public OrderAssert hasOrderRefId(Integer orderRefId) {
    isNotNull();
    assertThat(actual.getOrderRefId())
        .overridingErrorMessage("Expected the order reference id to be <%s> but was <%s>.", orderRefId,
            actual.getOrderRefId())
        .isEqualTo(orderRefId);
    return this;
  }

  public OrderAssert hasProduct(Product product) {
    isNotNull();
    boolean present = false;
    for (OrderProduct orderProduct : actual.getOrderProducts()) {
      if (orderProduct.getProduct().equals(product)) {
        present = true;
      }
    }
    assertThat(present)
        .overridingErrorMessage("Expected the order to have the product <%s> but he did not.", product.getName())
        .isTrue();
    return this;
  }

  public OrderAssert hasNotProduct(Product product) {
    isNotNull();
    boolean present = false;
    for (OrderProduct orderProduct : actual.getOrderProducts()) {
      if (orderProduct.getProduct().equals(product)) {
        present = true;
      }
    }
    assertThat(present)
        .overridingErrorMessage("Expected the order not to have the product <%s> but he did.", product.getName())
        .isFalse();
    return this;
  }

  public OrderAssert isSameAs(AbstractEntity entity) {
    isNotNull();
    assertThat(actual.hashCode())
        .overridingErrorMessage("Expected the hash code to be <%s> but was <%s>.", entity.hashCode(), actual.hashCode())
        .isEqualTo(entity.hashCode());
    return this;
  }

  public OrderAssert exists() {
    assertThat(actual).overridingErrorMessage("Expected the order to exist but it didn't.").isNotNull();
    return this;
  }

  public OrderAssert doesNotExist() {
    assertThat(actual).overridingErrorMessage("Expected the order not to exist but it did.").isNull();
    return this;
  }

}
