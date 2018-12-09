package com.thalasoft.butik.data.jpa.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public abstract class AbstractEntity {

  @Id
  // Compatible with H2, MySQL, Postgresql, Oracle
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @Version
  @Column(nullable = false)
  private Integer version;

  @CreationTimestamp
  protected LocalDateTime createdOn;

  @UpdateTimestamp
  protected LocalDateTime updatedOn;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getVersion() {
    return this.version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public LocalDateTime getCreatedOn() {
    return this.createdOn;
  }

  public void setCreatedOn(LocalDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public LocalDateTime getUpdatedOn() {
    return this.updatedOn;
  }

  public void setUpdatedOn(LocalDateTime updatedOn) {
    this.updatedOn = updatedOn;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }

    if (this.id == null || object == null || !(this.getClass().equals(object.getClass()))) {
      return false;
    }

    AbstractEntity that = (AbstractEntity) object;

    return this.id.equals(that.getId());
  }

  @Override
  public int hashCode() {
    return id == null ? 0 : id.hashCode();
  }

}
