package com.bloomscorp.aster.cart.dao.repository;

import com.bloomscorp.aster.cart.orm.AsterCartItem;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsterCartItemJpaRepository<
	   E extends Enum<E>,
	   R extends AsterUserRole<E>,
	   T extends NVerseTenant<E, R>,
       P extends AsterProduct,
	   C extends AsterCartItem<E, R, T, P>> extends JpaRepository<C, Long> {
	
	List<C> findByTenant(T tenant);
}
