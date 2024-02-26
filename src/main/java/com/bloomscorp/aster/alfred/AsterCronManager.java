package com.bloomscorp.aster.alfred;


import com.bloomscorp.alfred.cron.CronManager;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.tenant.orm.AsterTenant;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.aster.tenant.orm.USER_ROLE;

public class AsterCronManager<A extends AuthenticationLog> extends CronManager<
	AsterLogBook<A>,
	AsterLog,
	A,
	AsterTenant<USER_ROLE, AsterUserRole<USER_ROLE>>,
	USER_ROLE,
	AsterUserRole<USER_ROLE>
> {
	public AsterCronManager(AsterLogBook<A> logBook) {
		super(logBook);
	}
}
