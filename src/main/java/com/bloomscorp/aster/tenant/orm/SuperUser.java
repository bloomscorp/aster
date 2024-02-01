package com.bloomscorp.aster.tenant.orm;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class SuperUser<
	E extends Enum<E>,
	R extends UserRole<E>,
	T extends Tenant<E, R>
> {
	public abstract T getTenant();
}
