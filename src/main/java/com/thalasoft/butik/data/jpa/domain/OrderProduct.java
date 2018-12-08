package com.thalasoft.butik.data.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "shop_order_product")
@SequenceGenerator(name = "id_generator", sequenceName = "shop_order_product_id_seq", allocationSize = 10)
public class OrderProduct extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "shop_order_id", nullable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "shop_product_id", nullable = false)
    private Product product;
    @Column(nullable = false)
    private String price;

}
