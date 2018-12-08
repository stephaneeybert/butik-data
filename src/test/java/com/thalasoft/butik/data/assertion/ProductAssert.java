package com.thalasoft.butik.data.assertion;

import static org.assertj.core.api.Assertions.assertThat;

import com.thalasoft.butik.data.jpa.domain.AbstractEntity;
import com.thalasoft.butik.data.jpa.domain.Product;

import org.assertj.core.api.AbstractAssert;

public class ProductAssert extends AbstractAssert<ProductAssert, Product> {

  private ProductAssert(Product actual) {
    super(actual, ProductAssert.class);
  }

  public static ProductAssert assertThatProduct(Product actual) {
    return new ProductAssert(actual);
  }

  public ProductAssert hasId(Long id) {
    isNotNull();
    assertThat(actual.getId()).overridingErrorMessage("Expected the id to be <%s> but was <%s>.", id, actual.getId())
        .isEqualTo(id);
    return this;
  }

  public ProductAssert hasAnIdNotNull() {
    isNotNull();
    assertThat(actual.getId()).overridingErrorMessage("Expected the id to be not null but was null.").isNotNull();
    return this;
  }

  public ProductAssert hasName(String name) {
    isNotNull();
    assertThat(actual.getName())
        .overridingErrorMessage("Expected the name to be <%s> but was <%s>.", name, actual.getName())
        .isEqualTo(name.toString());
    return this;
  }

  public ProductAssert hasPrice(String price) {
    isNotNull();
    assertThat(actual.getPrice())
        .overridingErrorMessage("Expected the price to be <%s> but was <%s>.", price, actual.getPrice())
        .isEqualTo(price);
    return this;
  }

  public ProductAssert isSameAs(AbstractEntity entity) {
    isNotNull();
    assertThat(actual.hashCode())
        .overridingErrorMessage("Expected the hash code to be <%s> but was <%s>.", entity.hashCode(), actual.hashCode())
        .isEqualTo(entity.hashCode());
    return this;
  }

  public ProductAssert exists() {
    assertThat(actual).overridingErrorMessage("Expected the product to exist but it didn't.").isNotNull();
    return this;
  }

  public ProductAssert doesNotExist() {
    assertThat(actual).overridingErrorMessage("Expected the product not to exist but it did.").isNull();
    return this;
  }

}
