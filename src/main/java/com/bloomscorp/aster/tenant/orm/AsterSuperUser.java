package com.bloomscorp.aster.tenant.orm;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class AsterSuperUser<
	E extends Enum<E>,
	R extends AsterUserRole<E>,
	T extends AsterTenant<E, R>
> {
	public abstract T getTenant();
}
