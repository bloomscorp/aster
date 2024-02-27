package com.bloomscorp.aster.alfred;

import com.bloomscorp.alfred.LogBook;
import com.bloomscorp.alfred.adapter.ILogBookDAO;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.alfred.orm.LOG_TYPE;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.nverse.pojo.NVerseRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import com.bloomscorp.pastebox.Pastebox;

public abstract class AsterLogBook<
	A extends AuthenticationLog,
	T extends NVerseTenant<E, R>,
	E extends Enum<E>,
	R extends NVerseRole<E>
> extends LogBook<AsterLog, A, T, E, R> {

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
