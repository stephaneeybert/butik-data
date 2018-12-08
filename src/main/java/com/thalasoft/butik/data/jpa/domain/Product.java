package com.thalasoft.butik.data.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "shop_product")
@SequenceGenerator(name = "id_generator", sequenceName = "shop_product_id_seq", allocationSize = 10)
public class Product extends AbstractEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String price;

}
