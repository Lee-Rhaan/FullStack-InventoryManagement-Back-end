package com.example.InventoryManagement.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

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
    public int hashCode()
    {
        return Objects.hash(id, productName, productPrice, productQuantity, imageUrl);
    }

    @Override
    public boolean equals(Object object)
    {
        if(this == object)
            return true;
        if(object == null)
            return false;
        if(getClass() != object.getClass())
            return false;

        Product product = (Product) object;
        return Objects.equals(id, product.id) &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(productPrice, product.productPrice) &&
                Objects.equals(productQuantity, product.productQuantity) &&
                Objects.equals(imageUrl, product.imageUrl);
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
