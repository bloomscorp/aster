package com.bloomscorp.aster.alfred;

import com.bloomscorp.alfred.LogBook;
import com.bloomscorp.alfred.adapter.ILogBookDAO;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.alfred.orm.LOG_TYPE;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.tenant.orm.AsterTenant;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.aster.tenant.orm.USER_ROLE;
import com.bloomscorp.pastebox.Pastebox;

public abstract class AsterLogBook<A extends AuthenticationLog> extends LogBook<
	AsterLog,
	A,
	AsterTenant<USER_ROLE, AsterUserRole<USER_ROLE>>,
	USER_ROLE,
	AsterUserRole<USER_ROLE>
> {

	public AsterLogBook(ILogBookDAO<A, AsterLog> repository) {
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
}
