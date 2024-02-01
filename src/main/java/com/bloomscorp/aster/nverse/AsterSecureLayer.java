package com.bloomscorp.aster.nverse;

import com.bloomscorp.aster.tenant.orm.Tenant;
import com.bloomscorp.aster.tenant.orm.UserRole;
import com.bloomscorp.nverse.NVerseSecureLayer;

public abstract class AsterSecureLayer<
	T extends Tenant<E, R>,
	E extends Enum<E>,
	R extends UserRole<E>
> extends NVerseSecureLayer<T, E, R> {
}
