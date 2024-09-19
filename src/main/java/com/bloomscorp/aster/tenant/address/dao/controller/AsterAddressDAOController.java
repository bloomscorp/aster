package com.bloomscorp.aster.tenant.address.dao.controller;

import com.bloomscorp.aster.behemoth.AsterCRUDDAOController;
import com.bloomscorp.aster.tenant.address.dao.repository.AsterAddressJpaRepository;
import com.bloomscorp.aster.tenant.address.orm.AsterAddress;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;

import java.util.List;

public abstract class AsterAddressDAOController<
        E extends Enum<E>,
        R extends AsterUserRole<E>,
        T extends NVerseTenant<E, R>,
        A extends AsterAddress<E, R, T>,
        J extends AsterAddressJpaRepository<E, R, T, A>
        > extends AsterCRUDDAOController<A, J> {

    public AsterAddressDAOController(J repository) {
        super(repository);
    }

    public List<A> getAddressesByTenant(T tenant) {
        return this.getRepository().findByTenant(tenant);
    }
}
