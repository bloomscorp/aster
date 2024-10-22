package com.bloomscorp.aster.product.subcategory.controller;

import com.bloomscorp.alfred.LogBook;
import com.bloomscorp.alfred.cron.CronManager;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.behemoth.AsterCRUDController;
import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.subcategory.dao.controller.AsterProductSubCategoryDAOController;
import com.bloomscorp.aster.product.subcategory.dao.repository.AsterProductSubCategoryJpaRepository;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import com.bloomscorp.aster.restful.AsterProductSubCategoryResponse;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.NVerseAuthorityResolver;
import com.bloomscorp.nverse.NVerseGatekeeper;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import com.bloomscorp.nverse.sanitizer.HttpRequestDumpSanitizer;
import com.bloomscorp.raintree.RainTree;

public abstract class AsterProductSubCategoryController<
	B extends LogBook<AsterLog, A, T, E, R>,
	A extends AuthenticationLog,
	T extends NVerseTenant<E, R>,
	E extends Enum<E>,
	R extends AsterUserRole<E>,
	C extends CronManager<B, AsterLog, A, T, E, R>,
	CA extends AsterProductCategory,
	SCA extends AsterProductSubCategory,
	J extends AsterProductSubCategoryJpaRepository<SCA>
	> extends AsterCRUDController<B, A, T, E, R, C> {
	
	private final AsterProductSubCategoryDAOController<SCA, J> daoController;
	private final AsterProductSubCategoryResponse<CA, SCA> response;
	
	public AsterProductSubCategoryController(
		RainTree rainTree,
		B logBook,
		C cron,
		NVerseGatekeeper<T, E, R> gatekeeper,
		NVerseAuthorityResolver<T, E, R> authorityResolver,
		HttpRequestDumpSanitizer httpRequestDumpSanitizer,
		AsterProductSubCategoryDAOController<SCA, J> daoController,
		AsterProductSubCategoryResponse<CA, SCA> response
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
}
