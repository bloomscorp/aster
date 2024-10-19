package com.bloomscorp.aster.order.dao.repository;

import com.bloomscorp.aster.order.orm.AsterOrder;
import com.bloomscorp.aster.support.AsterExclude;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@AsterExclude
public interface AsterOrderJpaRepository<
        E extends Enum<E>,
        R extends AsterUserRole<E>,
        T extends NVerseTenant<E, R>,
        O extends AsterOrder<E, R, T>
        > extends JpaRepository<O, Long> {

    List<O> findByTenant(T tenant);
}
