package com.bloomscorp.aster.alfred;


import com.bloomscorp.aster.alfred.orm.AsterAuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.tenant.orm.AsterTenant;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;

public class AsterCronManager<
	L extends AsterLog,
	A extends AsterAuthenticationLog,
	T extends AsterTenant<E, R>,
	E extends Enum<E>,
	R extends AsterUserRole<E>
	> extends CronManager<
	LoomLogBook,
	L, A, T, E, R> {
	public AsterCronManager(LoomLogBook logBook) {
		super(logBook);
	}
}
