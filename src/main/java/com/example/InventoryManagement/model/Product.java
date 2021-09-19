package com.example.InventoryManagement.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String productName;
    private String productPrice;
    private Integer productQuantity;
    private String imageUrl;

    public Product(){}

    public Product(String productName, String productPrice, int productQuantity, String imageUrl)
    {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.imageUrl = imageUrl;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getProductPrice()
    {
        return productPrice;
    }

    public void setProductPrice(String productPrice)
    {
        this.productPrice = productPrice;
    }

    public Integer getProductQuantity()
    {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity)
    {
        this.productQuantity = productQuantity;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString()
    {
        return "Product {" +
                "productId='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", productQuantity='" + productQuantity + '\'' +
                ",  imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
