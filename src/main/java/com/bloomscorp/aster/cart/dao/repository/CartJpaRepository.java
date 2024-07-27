package com.bloomscorp.aster.cart.dao.repository;

import com.bloomscorp.aster.cart.orm.AsterCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartJpaRepository<C extends AsterCart> extends JpaRepository<C, Long> {
}
