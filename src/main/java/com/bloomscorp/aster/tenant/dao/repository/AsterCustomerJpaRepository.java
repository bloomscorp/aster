package com.bloomscorp.aster.tenant.dao.repository;

import com.bloomscorp.aster.support.AsterExclude;
import com.bloomscorp.aster.tenant.orm.AsterCustomer;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@AsterExclude
public interface AsterCustomerJpaRepository<
	E extends Enum<E>,
	R extends AsterUserRole<E>,
	T extends NVerseTenant<E, R>,
	C extends AsterCustomer<E, R, T>
	> extends JpaRepository<C, Long> {
	
	Optional<C> findByTenant(T tenant);
}
