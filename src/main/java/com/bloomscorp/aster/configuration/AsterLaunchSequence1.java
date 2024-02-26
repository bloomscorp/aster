package com.bloomscorp.aster.configuration;

import com.bloomscorp.alfred.LogBook;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.aster.alfred.AsterLogBook;
import com.bloomscorp.aster.alfred.orm.AsterAuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.tenant.orm.AsterTenant;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.aster.tenant.orm.USER_ROLE;
import com.bloomscorp.nverse.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Configuration
//@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class AsterLaunchSequence1<
	ALB extends LogBook<
		AsterLog,
		AsterAuthenticationLog,
		T, E, R
		>,
	T extends AsterTenant<E, R>,
	E extends Enum<E>,
	R extends AsterUserRole<E>
> {

//	private final RainTree rainTree;
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
//		AsterLogBook,
		ALB,
		AsterLog,
		AsterAuthenticationLog,
		T,
		E,
		R
	> nVerseExceptionHandlerFilter(
		boolean isProduction
	) {
//		return new NVerseExceptionHandlerFilter<>(
//			this.rainTree,
//			this.cronManager,
//			isProduction
//		);
		return null;
	}
}
