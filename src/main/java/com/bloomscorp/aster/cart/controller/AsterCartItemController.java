package com.bloomscorp.aster.cart.controller;

import com.bloomscorp.alfred.LogBook;
import com.bloomscorp.alfred.cron.CronManager;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.behemoth.AsterCRUDController;
import com.bloomscorp.nverse.NVerseAuthorityResolver;
import com.bloomscorp.nverse.NVerseGatekeeper;
import com.bloomscorp.nverse.pojo.NVerseRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import com.bloomscorp.nverse.sanitizer.HttpRequestDumpSanitizer;
import com.bloomscorp.raintree.RainTree;

public abstract class AsterCartItemController<
	B extends LogBook<AsterLog, A, T, E, R>,
	A extends AuthenticationLog,
	T extends NVerseTenant<E, R>,
	E extends Enum<E>,
	R extends NVerseRole<E>,
	C extends CronManager<B, AsterLog, A, T, E, R>
> extends AsterCRUDController<B, A, T, E, R, C> {

	public AsterCartItemController(
		RainTree rainTree,
		B logBook,
		C cron,
		NVerseGatekeeper<T, E, R> gatekeeper,
		NVerseAuthorityResolver<T, E, R> authorityResolver,
		HttpRequestDumpSanitizer httpRequestDumpSanitizer
	) {
		super(
			rainTree,
			logBook,
			cron,
			gatekeeper,
			authorityResolver,
			httpRequestDumpSanitizer
		);
	}
}
