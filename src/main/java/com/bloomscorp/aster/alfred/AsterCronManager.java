package com.bloomscorp.aster.alfred;


public class AsterCronManager extends CronManager<
	LoomLogBook,
	LoomLog,
	LoomAuthenticationLog,
	LoomTenant,
	USER_ROLE,
	UserRole
> {
	public AsterCronManager(LoomLogBook logBook) {
		super(logBook);
	}
}
