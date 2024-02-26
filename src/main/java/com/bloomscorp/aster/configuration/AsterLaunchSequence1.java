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
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Configuration
public class AsterLaunchSequence1<
	T extends AsterTenant<E, R>,
	E extends Enum<E>,
	R extends AsterUserRole<E>
> {

	@Bean
	@ConditionalOnMissingBean
	public NVerseAuthenticationEntryPoint authenticationEntryPoint() {
		return new NVerseAuthenticationEntryPoint();
	}

	@Bean
	@ConditionalOnMissingBean
	public NVerseHttpRequestFilter requestFilter() {
		return new NVerseHttpRequestFilter();
	}

	@Bean
	@ConditionalOnMissingBean
	public NVerseCORSConfigurationSource nVerseCORSConfigurationSource() {
		return new NVerseCORSConfigurationSource();
	}

	@Bean
	@ConditionalOnMissingBean
	public NVerseEmailValidator nVerseEmailValidator() {
		return new NVerseEmailValidator();
	}

	@Bean
	@ConditionalOnMissingBean
	public NVerseAuthenticationService nVerseAuthenticationService() {
		return new NVerseAuthenticationService();
	}

	public NVerseJWTService<T, E, R> nVerseJWTService(String jwtSecret) {
		return new NVerseJWTService<>(jwtSecret);
	}

	public NVersePasswordEncoder nVersePasswordEncoder(String pepper) {
		return new NVersePasswordEncoder(
			11,
			new SecureRandom(),
			pepper
		);
	}

	public NVerseExceptionHandlerFilter<
		AsterLogBook,
		AsterLog,
		AsterAuthenticationLog,
		TenantFacade,
		USER_ROLE,
		AsterUserRole<USER_ROLE>
	> nVerseExceptionHandlerFilter(
		RainTree rainTree,
		AsterCronManager cronManager,
		boolean isProduction
	) {
		return new NVerseExceptionHandlerFilter<>(
			rainTree,
			cronManager,
			isProduction
		);
	}
}
