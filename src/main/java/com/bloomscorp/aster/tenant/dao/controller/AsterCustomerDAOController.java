package com.bloomscorp.aster.tenant.dao.controller;

import com.bloomscorp.aster.behemoth.AsterCRUDDAOController;
import com.bloomscorp.aster.tenant.dao.repository.AsterCustomerJpaRepository;
import com.bloomscorp.aster.tenant.orm.AsterCustomer;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;

public abstract class AsterCustomerDAOController<
	E extends Enum<E>,
	R extends AsterUserRole<E>,
	T extends NVerseTenant<E, R>,
	C extends AsterCustomer<E, R, T>,
	J extends AsterCustomerJpaRepository<E, R, T, C>
	> extends AsterCRUDDAOController<C, J> {
	
	public AsterCustomerDAOController(J repository) {
		super(repository);
	}
	
	public C getProfile(T tenant) {
		return this.getRepository().findByTenant(tenant).orElse(null);
	}
	
	public C getCustomerById(Long id) {
		return this.getRepository().findById(id).orElse(null);
	}
}
