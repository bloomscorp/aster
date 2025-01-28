package com.bloomscorp.aster.order.dao.controller;

import com.bloomscorp.aster.behemoth.AsterCRUDDAOController;
import com.bloomscorp.aster.order.dao.repository.AsterOrderJpaRepository;
import com.bloomscorp.aster.order.orm.AsterOrder;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;

import java.util.List;

public abstract class AsterOrderDAOController<
        E extends Enum<E>,
        R extends AsterUserRole<E>,
        T extends NVerseTenant<E, R>,
        O extends AsterOrder<E, R, T, ?>,
        J extends AsterOrderJpaRepository<E, R, T, O>
        > extends AsterCRUDDAOController<O, J> {

    public AsterOrderDAOController(J repository) {
        super(repository);
    }

    public List<O> getOrdersByTenant(T tenant) {
        return this.getRepository().findByTenant(tenant);
    }
}
