package com.bloomscorp.aster.product.controller;

import com.bloomscorp.alfred.LogBook;
import com.bloomscorp.alfred.cron.CronManager;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.behemoth.AsterCRUDController;
import com.bloomscorp.aster.product.dao.controller.AsterProductDAOController;
import com.bloomscorp.aster.product.dao.repository.AsterProductJpaRepository;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.restful.AsterProductResponse;
import com.bloomscorp.nverse.NVerseAuthorityResolver;
import com.bloomscorp.nverse.NVerseGatekeeper;
import com.bloomscorp.nverse.NVerseHttpRequestWrapper;
import com.bloomscorp.nverse.pojo.NVerseRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import com.bloomscorp.nverse.sanitizer.HttpRequestDumpSanitizer;
import com.bloomscorp.raintree.RainTree;

public abstract class AsterProductController<
	B extends LogBook<AsterLog, A, T, E, R>,
	A extends AuthenticationLog,
	T extends NVerseTenant<E, R>,
	E extends Enum<E>,
	R extends NVerseRole<E>,
	C extends CronManager<B, AsterLog, A, T, E, R>,
	P extends AsterProduct,
	J extends AsterProductJpaRepository<P>
> extends AsterCRUDController<B, A, T, E, R, C> {

	private final AsterProductDAOController<P, J> daoController;
	public final AsterProductResponse<P> response;

	public AsterProductController(
		RainTree rainTree,
		B logBook,
		C cron,
		NVerseGatekeeper<T, E, R> gatekeeper,
		NVerseAuthorityResolver<T, E, R> authorityResolver,
		HttpRequestDumpSanitizer httpRequestDumpSanitizer,
		AsterProductDAOController<P, J> daoController,
		AsterProductResponse<P> response
	) {
		super(
			rainTree,
			logBook,
			cron,
			gatekeeper,
			authorityResolver,
			httpRequestDumpSanitizer
		);
		this.daoController = daoController;
		this.response = response;
	}

	public String getProductList(NVerseHttpRequestWrapper request) {
		return this.response.buildList(
			this.daoController.retrieveProducts()
		);
	}
}
