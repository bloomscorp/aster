package com.bloomscorp.aster.nverse;

import com.bloomscorp.aster.tenant.orm.AsterTenant;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.NVerseSecureLayer;

public abstract class AsterSecureLayer<
	T extends AsterTenant<E, R>,
	E extends Enum<E>,
	R extends AsterUserRole<E>
> extends NVerseSecureLayer<T, E, R> {
}
