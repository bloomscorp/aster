package com.bloomscorp.aster.configuration;

import com.bloomscorp.alfred.LogBook;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.aster.alfred.AsterCronManager;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.tenant.dao.controller.AsterTenantDAOController;
import com.bloomscorp.aster.tenant.orm.AsterTenant;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.*;
import com.bloomscorp.raintree.RainTree;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.SecureRandom;

public class AsterBeanFactory<
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

	public AsterBeanFactory(
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

	@Bean
	@ConditionalOnMissingBean
	public NVerseJWTService<T, E, R> nVerseJWTService() {
		return new NVerseJWTService<>(this.jwtSecret);
	}

	@Bean
	@ConditionalOnMissingBean
	public NVersePasswordEncoder nVersePasswordEncoder() {
		return new NVersePasswordEncoder(
			11,
			new SecureRandom(),
			this.pepper
		);
	}

	@Bean
	@ConditionalOnMissingBean
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
	@ConditionalOnMissingBean
	public NVerseEmailEncoder emailEncoder(NVerseEmailValidator emailValidator) {
		return new NVerseEmailEncoder(
			encoderKey,
			emailValidator,
			NVerseAES.SHA512
		);
	}

	@Bean
	@ConditionalOnMissingBean
	public <
		J extends JpaRepository<T, Long>,
		D extends AsterTenantDAOController<T, J, T, E, R>
	> NVerseUserDetailsService<T, E, R> nVerseUserDetailsService(
		NVerseEmailEncoder emailEncoder,
		D tenantDAOController
	) {
		return new NVerseUserDetailsService<>(
			emailEncoder,
			tenantDAOController
		);
	}

	@Bean
	@ConditionalOnMissingBean
	public NVerseAuthorityResolver<T, E, R> nVerseAuthorityResolver(
		NVerseJWTService<T, E, R> jwtService,
		NVerseUserDetailsService<T, E, R> userDetailsService
	) {
		return new NVerseAuthorityResolver<>(
			jwtService,
			userDetailsService
		);
	}

	@Bean
	@ConditionalOnMissingBean
	public NVerseRequestFilter<T, E, R> nVerseRequestFilter(
		NVerseJWTService<T, E, R> jwtService,
		NVerseUserDetailsService<T, E, R> userDetailsService
	) {
		return new NVerseRequestFilter<>(
			jwtService,
			userDetailsService
		);
	}
}
