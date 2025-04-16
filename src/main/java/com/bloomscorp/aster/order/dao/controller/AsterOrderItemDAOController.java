package com.bloomscorp.aster.order.dao.controller;


import com.bloomscorp.aster.behemoth.AsterCRUDDAOController;
import com.bloomscorp.aster.order.dao.repository.AsterOrderItemJpaRepository;
import com.bloomscorp.aster.order.orm.AsterOrder;
import com.bloomscorp.aster.order.orm.AsterOrderItem;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;

public abstract class AsterOrderItemDAOController<
	E extends Enum<E>,
	R extends AsterUserRole<E>,
	T extends NVerseTenant<E, R>,
	O extends AsterOrder<E, R, T, ?>,
	OT extends AsterOrderItem<E, R, T, O>,
	J extends AsterOrderItemJpaRepository<E, R, T, O, OT>
	> extends AsterCRUDDAOController<OT, J> {
	
	public AsterOrderItemDAOController(J repository) {
		super(repository);
	}
}
