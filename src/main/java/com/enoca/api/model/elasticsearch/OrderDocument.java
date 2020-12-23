package com.enoca.api.model.elasticsearch;

public class OrderDocument {

    private Long id;

    private String name;

    private int orderNumber;

    public OrderDocument() {
    }

    public OrderDocument(Long id, String name, int orderNumber) {
        this.id = id;
        this.name = name;
        this.orderNumber = orderNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "OrderDocument{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orderNumber=" + orderNumber +
                '}';
    }
}
