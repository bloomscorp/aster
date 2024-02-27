package com.bloomscorp.aster;

import com.bloomscorp.aster.alfred.AsterCronManager;
import com.bloomscorp.aster.alfred.AsterLogBook;
import com.bloomscorp.aster.alfred.orm.AsterAuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.tenant.orm.AsterTenant;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.aster.tenant.orm.TenantFacade;
import com.bloomscorp.aster.tenant.orm.USER_ROLE;
import com.bloomscorp.behemoth.controller.BehemothCRUDController;
import com.bloomscorp.nverse.NVerseAuthorityResolver;
import com.bloomscorp.nverse.NVerseGatekeeper;
import com.bloomscorp.nverse.sanitizer.HttpRequestDumpSanitizer;
import com.bloomscorp.raintree.RainTree;

public class CRUDController {}

//public abstract class CRUDController extends BehemothCRUDController<
//	AsterLogBook,
//	AsterLog,
//	AsterAuthenticationLog,
//	TenantFacade,
//	USER_ROLE,
//	AsterUserRole<USER_ROLE>
//> {
//	public CRUDController(
//		RainTree rainTree,
//		AsterLogBook logBook,
//		AsterCronManager cron,
//		NVerseGatekeeper<TenantFacade, USER_ROLE, UserRole> gatekeeper,
//		NVerseAuthorityResolver<LoomTenant, USER_ROLE, UserRole> authorityResolver,
//		HttpRequestDumpSanitizer httpRequestDumpSanitizer
//	) {
//		super(
//			rainTree,
//			logBook,
//			cron,
//			gatekeeper,
//			authorityResolver,
//			httpRequestDumpSanitizer
//		);
//	}
//}
