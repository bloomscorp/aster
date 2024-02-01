package com.bloomscorp.aster.nverse;

import com.bloomscorp.aster.tenant.orm.Tenant;
import com.bloomscorp.aster.tenant.orm.UserRole;
import com.bloomscorp.nverse.NVerseGatekeeper;

public abstract class AsterGatekeeper<
	T extends Tenant<E, R>,
	E extends Enum<E>,
	R extends UserRole<E>,
	S extends AsterSecureLayer<T, E, R>
> extends NVerseGatekeeper<T, E, R> {

	public AsterGatekeeper(S secureLayer) {
		super(secureLayer);
	}
}
