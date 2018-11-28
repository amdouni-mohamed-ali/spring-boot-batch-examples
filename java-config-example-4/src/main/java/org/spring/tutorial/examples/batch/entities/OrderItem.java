package org.spring.tutorial.examples.batch.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

    private Integer orderItemId;
    private String orderItemProductId;
    private Integer orderItemQuantity;
    private Float orderItemProdcutPrice;

    @Id
    @Column(name = "ORDER_ITEM_ID")
    public Integer getOrderItemId() {
        return orderItemId;
    }

    public OrderItem setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
        return this;
    }

    @Column(name = "ORDER_PRODUCT_ID")
    public String getOrderItemProductId() {
        return orderItemProductId;
    }

    public OrderItem setOrderItemProductId(String orderItemProductId) {
        this.orderItemProductId = orderItemProductId;
        return this;
    }

    @Column(name = "ORDER_ITEM_QUANTITY")
    public Integer getOrderItemQuantity() {
        return orderItemQuantity;
    }

    public OrderItem setOrderItemQuantity(Integer orderItemQuantity) {
        this.orderItemQuantity = orderItemQuantity;
        return this;
    }

    @Column(name = "ORDER_ITEM_PRODUCT_PRICE")
    public Float getOrderItemProdcutPrice() {
        return orderItemProdcutPrice;
    }

    public OrderItem setOrderItemProdcutPrice(Float orderItemProdcutPrice) {
        this.orderItemProdcutPrice = orderItemProdcutPrice;
        return this;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", orderItemProductId='" + orderItemProductId + '\'' +
                ", orderItemQuantity=" + orderItemQuantity +
                ", orderItemProdcutPrice=" + orderItemProdcutPrice +
                '}';
    }
}
