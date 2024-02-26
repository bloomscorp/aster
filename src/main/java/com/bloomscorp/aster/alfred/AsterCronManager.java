package com.bloomscorp.aster.alfred;


import com.bloomscorp.alfred.cron.CronManager;
import com.bloomscorp.aster.alfred.orm.AsterAuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.support.Constant;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.aster.tenant.orm.TenantFacade;
import com.bloomscorp.aster.tenant.orm.USER_ROLE;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(Constant.SCOPE_SINGLETON)
public class AsterCronManager extends CronManager<
	AsterLogBook,
	AsterLog,
	AsterAuthenticationLog,
	TenantFacade,
	USER_ROLE,
	AsterUserRole<USER_ROLE>
> {
	public AsterCronManager(AsterLogBook logBook) {
		super(logBook);
	}
}
