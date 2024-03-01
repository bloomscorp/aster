package com.bloomscorp.aster.authentication;

import com.bloomscorp.alfred.LogBook;
import com.bloomscorp.alfred.cron.CronManager;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.behemoth.AsterCRUDController;
import com.bloomscorp.aster.nverse.service.AsterAuth0Service;
import com.bloomscorp.aster.restful.AuthorityTokenResponse;
import com.bloomscorp.aster.support.Constant;
import com.bloomscorp.aster.tenant.dao.controller.AsterTenantDAOController;
import com.bloomscorp.aster.tenant.orm.AsterTenant;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.hastar.AuthorizationException;
import com.bloomscorp.nverse.*;
import com.bloomscorp.nverse.pojo.NVERSE_AUTH_PROVIDER;
import com.bloomscorp.nverse.sanitizer.HttpRequestDumpSanitizer;
import com.bloomscorp.pastebox.Pastebox;
import com.bloomscorp.raintree.RainTree;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;

import static com.bloomscorp.aster.support.LogMessage.UNAUTH_AUTH_TOKEN_REQUEST;

public abstract class AsterAuthenticationController<
	B extends LogBook<AsterLog, A, T, E, R>,
	A extends AuthenticationLog,
	T extends AsterTenant<E, R>,
	E extends Enum<E>,
	R extends AsterUserRole<E>,
	C extends CronManager<B, AsterLog, A, T, E, R>,
	J extends JpaRepository<T, Long>,
	D extends AsterTenantDAOController<T, J, T, E, R>,
	O extends AsterAuth0Service
> extends AsterCRUDController<B, A, T, E, R, C> {

	private final AuthenticationManager authenticationManager;
	private final NVerseAuthenticationService nVerseService;
	private final NVerseUserDetailsService<T, E, R> userDetailsService;
	private final NVerseJWTService<T, E, R> jwtService;
	private final C cron;
	private final AuthorityTokenResponse<R> response;
	private final O auth0;
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
		AuthorityTokenResponse<R> response,
		O auth0
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
		this.auth0 = auth0;
	}

	public ResponseEntity<?> createAuthenticationTokenUsingEmailID(
		@NotNull NVerseRequest authRequest
	) throws DisabledException, BadCredentialsException {

		String username = authRequest.username();
		String password = authRequest.password();

		if (Pastebox.isEmptyString(username) || Pastebox.isEmptyString(password))
			throw new AuthorizationException(AuthorizationException.AECx01);

		// TODO: this is the reason userDetailsService.loadUser is called twice
		this.nVerseService.authenticate(username, password, this.authenticationManager);

		NVerseUser<T, E, R> user = this.userDetailsService.loadUserByUsername(username);
		String token = this.jwtService.generateToken(user);

//		new Thread(() -> {
//			user.getTenant().setLastAccessTime(Pastebox.getCurrentTimeInMillis());
//			user.getTenant().setProvider(NVERSE_AUTH_PROVIDER.BASIC);
//			this.daoController.updateTenant(user.getTenant());
//		}).start();

		this.cron.scheduleLoginLogTask(user.getTenant());

		return ResponseEntity.ok(new NVerseResponse(token));
	}

//	public ResponseEntity<?> createAuthenticationTokenUsingSocialID(
//		@NotNull NVerseSocialRequest authRequest
//	) throws DisabledException, BadCredentialsException {
//
//		String username = authRequest.username();
//		String auth0JWTToken = authRequest.password();
//		NVERSE_AUTH_PROVIDER provider = authRequest.provider();
//
//		if (Pastebox.isEmptyString(username) ||
//			Pastebox.isEmptyString(auth0JWTToken) ||
//			!Pastebox.isInEnum(provider, NVERSE_AUTH_PROVIDER.class)
//		) {
//			throw new AuthorizationException(AuthorizationException.AECx01);
//		}
//
//		if (!this.auth0.validateToken(auth0JWTToken, username)) {
//			throw new BadCredentialsException("");
//		};
//
//		String password = this.auth0.getUserFromToken(auth0JWTToken);
////        this.nVerseService.authenticate(username, password, this.authenticationManager);
//
//		NVerseUser<LoomTenant, USER_ROLE, UserRole> user = this.userDetailsService.loadUserByUsername(username);
//		String token = this.jwtService.generateToken(user);
//
//		new Thread(() -> {
//			user.getTenant().setEmailVerified(true);
//			user.getTenant().setLastAccessTime(Pastebox.getCurrentTimeInMillis());
//			user.getTenant().setProvider(provider);
//			user.getTenant().setPassword(this.passwordEncoder.encode(password));
//			this.daoController.updateTenant(user.getTenant());
//		}).start();
//
//		this.cron.scheduleLoginLogTask(user.getTenant());
//
//		return ResponseEntity.ok(new NVerseResponse(token));
//	}

	public String getAuthorityToken(
		NVerseHttpRequestWrapper request,
		int surveillanceCode
	) {
		return this.getEntity(
			request,
			"getAuthorityToken",
			surveillanceCode,
			UNAUTH_AUTH_TOKEN_REQUEST,
			() -> this.response.buildList(
				this
					.getAuthorityResolver()
					.resolveUserInformationFromAuthorizationToken(
						request.getHeader(Constant.REQUEST_HEADER_AUTHORIZATION)
					)
					.getRoles()
			)
		);
	}

	public ResponseEntity<?> validateProvider(
		@RequestBody @NotNull NVerseAuthProviderValidationRequest validationRequest
	) {

		String username = validationRequest.username();
		NVERSE_AUTH_PROVIDER provider = validationRequest.provider();

		NVerseUser<T, E, R> user = this.userDetailsService.loadUserByUsername(username);

		boolean isProviderValid = this.nVerseService.validateAuthProvider(user.getTenant(), provider, true);

		return ResponseEntity.ok(
			new NVerseProviderResponse(
				isProviderValid,
				isProviderValid ? -1 : user.getTenant().getProvider().ordinal()
			)
		);
	}
}
