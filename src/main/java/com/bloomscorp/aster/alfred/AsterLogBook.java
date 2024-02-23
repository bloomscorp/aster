package com.bloomscorp.aster.alfred;

import com.bloomscorp.alfred.LogBook;
import com.bloomscorp.alfred.adapter.ILogBookDAO;
import com.bloomscorp.alfred.orm.AUTH_ACTION_ENUM;
import com.bloomscorp.alfred.orm.LOG_TYPE;
import com.bloomscorp.aster.alfred.orm.AsterAuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.tenant.orm.AsterTenant;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.pastebox.Pastebox;

public class AsterLogBook<E extends Enum<E>> extends LogBook<
	AsterLog,
	AsterAuthenticationLog,
	AsterTenant<E, AsterUserRole<E>>,
	E,
	AsterUserRole<E>
> {

	public AsterLogBook(ILogBookDAO<AsterAuthenticationLog, AsterLog> repository) {
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

	@Override
	public AsterAuthenticationLog buildAuthenticationLogInstance(
		AUTH_ACTION_ENUM action,
		LoomTenant user
	) {
		return AsterAuthenticationLog
			.builder()
			.action(action)
			.user(user)
			.build();
	}
}
