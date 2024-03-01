package com.bloomscorp.aster.authentication;

import com.bloomscorp.alfred.LogBook;
import com.bloomscorp.alfred.cron.CronManager;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.behemoth.AsterCRUDController;
import com.bloomscorp.aster.restful.AuthorityTokenResponse;
import com.bloomscorp.aster.tenant.dao.controller.AsterTenantDAOController;
import com.bloomscorp.aster.tenant.orm.AsterTenant;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.*;
import com.bloomscorp.nverse.pojo.NVerseRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import com.bloomscorp.nverse.sanitizer.HttpRequestDumpSanitizer;
import com.bloomscorp.raintree.RainTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class AsterAuthenticationController<
	B extends LogBook<AsterLog, A, T, E, R>,
	A extends AuthenticationLog,
	T extends AsterTenant<E, R>,
	E extends Enum<E>,
	R extends AsterUserRole<E>,
	C extends CronManager<B, AsterLog, A, T, E, R>,
	J extends JpaRepository<T, Long>,
	D extends AsterTenantDAOController<T, J, T, E, R>
> extends AsterCRUDController<B, A, T, E, R, C> {

	private final AuthenticationManager authenticationManager;
	private final NVerseAuthenticationService nVerseService;
	private final NVerseUserDetailsService<T, E, R> userDetailsService;
	private final NVerseJWTService<T, E, R> jwtService;
	private final C cron;
	private final AuthorityTokenResponse<R> response;
	private final Auth0Service auth0;
	private final D daoController;
	private final PasswordEncoder passwordEncoder;

	public AsterAuthenticationController(
		RainTree rainTree,
		B logBook,
		C cron,
		NVerseGatekeeper<T, E, R> gatekeeper,
		NVerseAuthorityResolver<T, E, R> authorityResolver,
		HttpRequestDumpSanitizer httpRequestDumpSanitizer,
		AuthenticationManager authenticationManager,
		NVerseAuthenticationService nVerseService,
		NVerseUserDetailsService<T, E, R> userDetailsService,
		NVerseJWTService<T, E, R> jwtService,
		D daoController,
		PasswordEncoder passwordEncoder,
		AuthorityTokenResponse<R> response
	) {
		super(
			rainTree,
			logBook,
			cron,
			gatekeeper,
			authorityResolver,
			httpRequestDumpSanitizer
		);
		this.authenticationManager = authenticationManager;
		this.nVerseService = nVerseService;
		this.userDetailsService = userDetailsService;
		this.jwtService = jwtService;
		this.cron = cron;
		this.daoController = daoController;
		this.passwordEncoder = passwordEncoder;
		this.response = response;
	}
}
