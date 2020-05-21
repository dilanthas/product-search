package com.telenor.productsearch.entity;


import com.telenor.productsearch.constants.ErrorConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Product type"+ ErrorConstants.CANNOT_BE_EMPTY)
    @Column(name = "product_type",nullable = false)
    private String type;

    @NotNull(message = "Product price"+ ErrorConstants.CANNOT_BE_EMPTY)
    @Column(name = "price",nullable = false)
    private BigDecimal price;

    @NotNull(message = "Product street name"+ ErrorConstants.CANNOT_BE_EMPTY)
    @Column(name = "street_name",nullable = false)
    private String streetName;

    @NotNull(message = "Product city name"+ ErrorConstants.CANNOT_BE_EMPTY)
    @Column(name = "city_name",nullable = false)
    private String cityName;

    @NotNull(message = "Product property"+ ErrorConstants.CANNOT_BE_EMPTY)
    @Column(name = "property",nullable = false)
    private String property;

    @Column(name = "color")
    private String color;

    @Column(name = "gbLimit")
    private Integer gbLimit;








}
