package com.bloomscorp.aster.alfred;

import com.bloomscorp.alfred.LogBook;
import com.bloomscorp.alfred.cron.CronManager;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.alfred.orm.Log;
import com.bloomscorp.aster.alfred.orm.AsterAuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.aster.tenant.orm.TenantFacade;
import com.bloomscorp.aster.tenant.orm.USER_ROLE;
import com.bloomscorp.nverse.pojo.NVerseRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;

public abstract class TestCronManager<
	B extends LogBook<AsterLog, A, T, E, R>,
	A extends AuthenticationLog,
	T extends NVerseTenant<E, R>,
	E extends Enum<E>,
	R extends NVerseRole<E>
> extends CronManager<B, AsterLog, A, T, E, R> {
	public TestCronManager(B logBook) {
		super(logBook);
	}
}
