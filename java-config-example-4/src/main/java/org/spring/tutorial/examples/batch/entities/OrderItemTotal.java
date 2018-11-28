package org.spring.tutorial.examples.batch.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderItemTotal {

    private Integer orderItemId;
    private Float orderItemSubTotal;

    @Id
    public Integer getOrderItemId() {
        return orderItemId;
    }

    public OrderItemTotal setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
        return this;
    }

    public Float getOrderItemSubTotal() {
        return orderItemSubTotal;
    }

    public OrderItemTotal setOrderItemSubTotal(Float orderItemSubTotal) {
        this.orderItemSubTotal = orderItemSubTotal;
        return this;
    }

    @Override
    public String toString() {
        return "OrderItemTotal{" +
                "orderItemId=" + orderItemId +
                ", orderItemSubTotal=" + orderItemSubTotal +
                '}';
    }
}
