package com.bloomscorp.aster.product.dao.controller;

import com.bloomscorp.aster.behemoth.AsterCRUDDAOController;
import com.bloomscorp.aster.product.dao.repository.AsterProductVariantJpaRepository;
import com.bloomscorp.aster.product.orm.AsterProductVariant;

public class AsterProductVariantDAOController<
	V extends AsterProductVariant,
	R extends AsterProductVariantJpaRepository<V>
	> extends AsterCRUDDAOController<V, R> {
	
	public AsterProductVariantDAOController(R repository) {
		super(repository);
	}
}
