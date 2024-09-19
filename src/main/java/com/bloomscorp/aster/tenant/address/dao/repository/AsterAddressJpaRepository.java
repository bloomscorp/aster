package com.bloomscorp.aster.tenant.address.dao.repository;

import com.bloomscorp.aster.tenant.address.orm.AsterAddress;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsterAddressJpaRepository<
        E extends Enum<E>,
        R extends AsterUserRole<E>,
        T extends NVerseTenant<E, R>,
        A extends AsterAddress<E, R, T>
        > extends JpaRepository<A, Long> {

    List<A> findByTenant(T tenant);
}
