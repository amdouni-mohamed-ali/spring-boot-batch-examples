package org.spring.tutorial.examples.batch.dao;

import org.spring.tutorial.examples.batch.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderItem extends JpaRepository<OrderItem, Long> {
}
