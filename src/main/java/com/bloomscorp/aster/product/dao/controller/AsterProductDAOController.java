package com.bloomscorp.aster.product.dao.controller;

import com.bloomscorp.aster.behemoth.AsterCRUDDAOController;
import com.bloomscorp.aster.product.dao.repository.AsterProductJpaRepository;
import com.bloomscorp.aster.product.orm.AsterProduct;

import java.util.List;

public abstract class AsterProductDAOController<
	P extends AsterProduct,
	R extends AsterProductJpaRepository<P>
> extends AsterCRUDDAOController<P, R> {

	public AsterProductDAOController(R repository) {
		super(repository);
	}

	public List<P> retrieveProducts() {
		List<P> products = this.getRepository().findAll();
		return products;
	}
}
