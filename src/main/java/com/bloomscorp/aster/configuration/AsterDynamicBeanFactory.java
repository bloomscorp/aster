package com.bloomscorp.aster.configuration;

import com.bloomscorp.aster.alfred.AsterCronManager;
import com.bloomscorp.aster.alfred.AsterLogBook;
import com.bloomscorp.aster.alfred.orm.AsterAuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.tenant.orm.AsterTenant;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.aster.tenant.orm.TenantFacade;
import com.bloomscorp.aster.tenant.orm.USER_ROLE;
import com.bloomscorp.nverse.*;
import com.bloomscorp.raintree.RainTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

public class AsterDynamicBeanFactory<
	T extends AsterTenant<E, R>,
	E extends Enum<E>,
	R extends AsterUserRole<E>
> {

	private final String pepper;
	private final String jwtSecret;
	private final String encoderKey;
	private final boolean isProduction;

	public AsterDynamicBeanFactory(
		String pepper,
		String jwtSecret,
		String encoderKey,
		boolean isProduction
	) {
		this.pepper = pepper;
		this.jwtSecret = jwtSecret;
		this.encoderKey = encoderKey;
		this.isProduction = isProduction;
	}

	@Bean
	public NVerseJWTService<T, E, R> nVerseJWTService() {
		return new NVerseJWTService<>(this.jwtSecret);
	}

	@Bean
	public NVersePasswordEncoder nVersePasswordEncoder() {
		return new NVersePasswordEncoder(
			11,
			new SecureRandom(),
			this.pepper
		);
	}

	@Bean
	public NVerseExceptionHandlerFilter<
		AsterLogBook,
		AsterLog,
		AsterAuthenticationLog,
		TenantFacade,
		USER_ROLE,
		AsterUserRole<USER_ROLE>
	> nVerseExceptionHandlerFilter(
		RainTree rainTree,
		AsterCronManager cronManager
	) {
		return new NVerseExceptionHandlerFilter<>(
			rainTree,
			cronManager,
			this.isProduction
		);
	}

	@Bean
	public NVerseEmailEncoder emailEncoder(NVerseEmailValidator emailValidator) {
		return new NVerseEmailEncoder(
			encoderKey,
			emailValidator,
			NVerseAES.SHA512
		);
	}
}
