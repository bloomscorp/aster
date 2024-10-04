package com.bloomscorp.aster.cart.controller;

import com.bloomscorp.alfred.LogBook;
import com.bloomscorp.alfred.cron.CronManager;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.behemoth.AsterCRUDController;
import com.bloomscorp.aster.cart.dao.controller.AsterCartItemDAOController;
import com.bloomscorp.aster.cart.dao.repository.AsterCartItemJpaRepository;
import com.bloomscorp.aster.cart.orm.AsterCartItem;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.restful.AsterCartItemResponse;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.NVerseAuthorityResolver;
import com.bloomscorp.nverse.NVerseGatekeeper;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import com.bloomscorp.nverse.sanitizer.HttpRequestDumpSanitizer;
import com.bloomscorp.raintree.RainTree;

public abstract class AsterCartItemController<
	B extends LogBook<AsterLog, A, T, E, R>,
	A extends AuthenticationLog,
	T extends NVerseTenant<E, R>,
	E extends Enum<E>,
	R extends AsterUserRole<E>,
	C extends CronManager<B, AsterLog, A, T, E, R>,
	P extends AsterProduct,
	CI extends AsterCartItem<E, R, T, P>,
	J extends AsterCartItemJpaRepository<E, R, T, P, CI>
> extends AsterCRUDController<B, A, T, E, R, C> {
	
	public final AsterCartItemResponse<E, R, T, P, CI> response;
	public final AsterCartItemDAOController<E, R, T, P, CI, J> daoController;

	public AsterCartItemController(
		RainTree rainTree,
		B logBook,
		C cron,
		NVerseGatekeeper<T, E, R> gatekeeper,
		NVerseAuthorityResolver<T, E, R> authorityResolver,
		HttpRequestDumpSanitizer httpRequestDumpSanitizer,
		AsterCartItemResponse<E, R, T, P, CI> response,
		AsterCartItemDAOController<E, R, T, P, CI, J> daoController
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
	
	public String getCartItemList(T tenant) {
		return this.response.buildList(
			this.daoController.getCartItemsByTenant(tenant)
		);
	}
}
