package com.bloomscorp.aster.configuration;

import com.bloomscorp.nverse.*;
import com.bloomscorp.raintree.RainTree;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Configuration
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class AsterLaunchSequence1 {

	private final RainTree rainTree;
//	private final LoomCronManager cronManager;

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

//	@Bean
//	@ConditionalOnMissingBean
//	public NVerseJWTService<LoomTenant, USER_ROLE, UserRole> nVerseJWTService(
//		@Value("${nverse.jwt.secret}") String jwtSecret) {
//		return new NVerseJWTService<>(jwtSecret);
//	}
//
//	@Bean
//	@ConditionalOnMissingBean
//	public NVersePasswordEncoder nVersePasswordEncoder(
//		@Value("${nverse.encoder.pepper}") String pepper
//	) {
//		return new NVersePasswordEncoder(
//			11,
//			new SecureRandom(),
//			pepper
//		);
//	}
//
//	@Bean
//	@ConditionalOnMissingBean
//	public NVerseExceptionHandlerFilter<
//		LoomLogBook,
//		LoomLog,
//		LoomAuthenticationLog,
//		LoomTenant,
//		USER_ROLE,
//		UserRole
//		> nVerseExceptionHandlerFilter(
//		@Value("${loom.config.production}") boolean isProduction
//	) {
//		return new NVerseExceptionHandlerFilter<>(
//			this.rainTree,
//			this.cronManager,
//			isProduction
//		);
//	}
}
