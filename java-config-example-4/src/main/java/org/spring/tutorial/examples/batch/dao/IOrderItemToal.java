package org.spring.tutorial.examples.batch.dao;


import org.spring.tutorial.examples.batch.entities.OrderItemTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderItemToal extends JpaRepository<OrderItemTotal, Long> {
}
