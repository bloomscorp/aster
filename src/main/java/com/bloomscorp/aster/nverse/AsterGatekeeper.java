package com.bloomscorp.aster.nverse;

import com.bloomscorp.aster.tenant.orm.AsterTenant;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.NVerseGatekeeper;

public abstract class AsterGatekeeper<
	T extends AsterTenant<E, R>,
	E extends Enum<E>,
	R extends AsterUserRole<E>,
	S extends AsterSecureLayer<T, E, R>
> extends NVerseGatekeeper<T, E, R> {

	public AsterGatekeeper(S secureLayer) {
		super(secureLayer);
	}
}
