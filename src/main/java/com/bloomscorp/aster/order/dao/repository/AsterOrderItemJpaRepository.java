package com.bloomscorp.aster.order.dao.repository;

import com.bloomscorp.aster.order.orm.AsterOrder;
import com.bloomscorp.aster.order.orm.AsterOrderItem;
import com.bloomscorp.aster.support.AsterExclude;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import org.springframework.data.jpa.repository.JpaRepository;

@AsterExclude
public interface AsterOrderItemJpaRepository<
	E extends Enum<E>,
	R extends AsterUserRole<E>,
	T extends NVerseTenant<E, R>,
	O extends AsterOrder<E, R, T, ?>,
	OT extends AsterOrderItem<E, R, T, O>
	> extends JpaRepository<OT, Long> {
}
