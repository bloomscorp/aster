package com.bloomscorp.aster.alfred;

import com.bloomscorp.alfred.LogBook;
import com.bloomscorp.alfred.adapter.ILogBookDAO;
import com.bloomscorp.alfred.orm.AUTH_ACTION_ENUM;
import com.bloomscorp.alfred.orm.LOG_TYPE;
import com.bloomscorp.aster.alfred.orm.AsterAuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.aster.tenant.orm.TenantFacade;
import com.bloomscorp.aster.tenant.orm.USER_ROLE;
import com.bloomscorp.nverse.pojo.NVerseRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import com.bloomscorp.pastebox.Pastebox;

public abstract class TestLogBook<
	T extends NVerseTenant<E, R>,
	E extends Enum<E>,
	R extends NVerseRole<E>
> extends LogBook<
	AsterLog,
	AsterAuthenticationLog,
	T,
	E,
	R
> {

	public TestLogBook(ILogBookDAO<AsterAuthenticationLog, AsterLog> repository) {
		super(repository);
	}

	@Override
	public AsterLog buildLogInstance(
		String logger,
		LOG_TYPE logType,
		String message,
		String dataDump
	) {
		return AsterLog
			.builder()
			.logger(logger)
			.logType(logType)
			.message(message)
			.dataDump(dataDump)
			.time(Pastebox.getCurrentTimeInMillis())
			.build();
	}

//	@Override
//	public AsterAuthenticationLog buildAuthenticationLogInstance(
//		AUTH_ACTION_ENUM action,
//		TenantFacade tenantFacade
//	) {
//		return AsterAuthenticationLog
//			.builder()
//			.action(action)
//			.tenant(tenantFacade)
//			.build();
//	}
}
