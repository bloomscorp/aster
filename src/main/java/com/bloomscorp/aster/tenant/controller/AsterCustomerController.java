package com.bloomscorp.aster.tenant.controller;

import com.bloomscorp.alfred.LogBook;
import com.bloomscorp.alfred.cron.CronManager;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.behemoth.AsterCRUDController;
import com.bloomscorp.aster.restful.AsterCustomerResponse;
import com.bloomscorp.aster.tenant.dao.controller.AsterCustomerDAOController;
import com.bloomscorp.aster.tenant.dao.repository.AsterCustomerJpaRepository;
import com.bloomscorp.aster.tenant.orm.AsterCustomer;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.NVerseAuthorityResolver;
import com.bloomscorp.nverse.NVerseGatekeeper;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import com.bloomscorp.nverse.sanitizer.HttpRequestDumpSanitizer;
import com.bloomscorp.raintree.RainTree;

public abstract class AsterCustomerController<
	B extends LogBook<AsterLog, A, T, E, R>,
	A extends AuthenticationLog,
	T extends NVerseTenant<E, R>,
	E extends Enum<E>,
	R extends AsterUserRole<E>,
	C extends CronManager<B, AsterLog, A, T, E, R>,
	CU extends AsterCustomer<E, R, T>,
	J extends AsterCustomerJpaRepository<E, R, T, CU>
	> extends AsterCRUDController<B, A, T, E, R, C> {
	
	public final AsterCustomerResponse<E, R, T, CU> response;
	public final AsterCustomerDAOController<E, R, T, CU, J> daoController;
	
	public AsterCustomerController(
		RainTree rainTree,
		B logBook, C cron,
		NVerseGatekeeper<T, E, R> gatekeeper,
		NVerseAuthorityResolver<T, E, R> authorityResolver,
		HttpRequestDumpSanitizer httpRequestDumpSanitizer,
		AsterCustomerResponse<E, R, T, CU> response,
		AsterCustomerDAOController<E, R, T, CU, J> daoController
	) {
		super(
			rainTree,
			logBook,
			cron,
			gatekeeper,
			authorityResolver,
			httpRequestDumpSanitizer
		);
		this.response = response;
		this.daoController = daoController;
	}
}
