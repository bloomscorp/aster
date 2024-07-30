package com.bloomscorp.aster.cart.dao.repository;

import com.bloomscorp.aster.cart.orm.AsterCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsterCartItemJpaRepository<C extends AsterCartItem> extends JpaRepository<C, Long> {
}
