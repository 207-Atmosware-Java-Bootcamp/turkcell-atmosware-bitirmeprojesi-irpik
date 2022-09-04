package com.kadirirpik.entities;

import com.kadirirpik.entities.abstracts.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="products")
public class Product extends BaseEntity {
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_trade")
    private String productTrade;
    @Column(name = "product_serial_number")
    private String productSerialNumber;
    @Column(name = "product_price")
    private double productPrice;

}
