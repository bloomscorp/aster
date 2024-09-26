package com.bloomscorp.aster.cart.dao.controller;

import com.bloomscorp.aster.behemoth.AsterCRUDDAOController;
import com.bloomscorp.aster.cart.dao.repository.AsterCartItemJpaRepository;
import com.bloomscorp.aster.cart.orm.AsterCartItem;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;

import java.util.List;

public class AsterCartItemDAOController<
	   E extends Enum<E>,
	   R extends AsterUserRole<E>,
	   T extends NVerseTenant<E, R>,
	   P extends AsterProduct,
	   C extends AsterCartItem<E, R, T, P>,
	   J extends AsterCartItemJpaRepository<E, R, T, P, C>
       > extends AsterCRUDDAOController<C, J> {
	
	public AsterCartItemDAOController(J repository) {
		super(repository);
	}
	
	public List<C> getCartItemsByTenant(T tenant) {
		return this.getRepository().findByTenant(tenant);
	}
}
