package com.bloomscorp.aster.configuration;

import com.bloomscorp.alfred.LogBook;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.aster.alfred.AsterCronManager;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.tenant.orm.AsterTenant;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.*;
import com.bloomscorp.raintree.RainTree;
import org.springframework.context.annotation.Bean;

import java.security.SecureRandom;

public class AsterDynamicBeanFactory<
	C extends AsterCronManager<L, A, T, E, R>,
	L extends LogBook<AsterLog, A, T, E, R>,
	A extends AuthenticationLog,
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
	public NVerseExceptionHandlerFilter<L, AsterLog, A, T, E, R> nVerseExceptionHandlerFilter(
		RainTree rainTree,
		C cronManager
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

//	@Bean
//	public NVerseUserDetailsService<LoomTenant, USER_ROLE, UserRole> nVerseUserDetailsService(
//		NVerseEmailEncoder emailEncoder
//	) {
//		return new NVerseUserDetailsService<>(
//			emailEncoder,
//			this.tenantDAOController
//		);
//	}
}
