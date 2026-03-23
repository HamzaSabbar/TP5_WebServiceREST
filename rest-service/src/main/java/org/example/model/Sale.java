package org.example.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Sale {
    private Long id;
    private String date;
    private String product;
    private int quantity;
    private double price;
    private double total;
    private String customer;
    private String region;

    public Sale() {
    }

    public Sale(Long id, String date, String product, int quantity, double price, double total, String customer, String region) {
        this.id = id;
        this.date = date;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.customer = customer;
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", product='" + product + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", total=" + total +
                ", customer='" + customer + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
