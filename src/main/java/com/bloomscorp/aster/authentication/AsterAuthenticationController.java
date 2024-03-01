package com.bloomscorp.aster.authentication;

public abstract class AsterAuthenticationController {

//	private final AuthenticationManager authenticationManager;
//	private final NVerseAuthenticationService nVerseService;
//	private final NVerseUserDetailsService<LoomTenant, USER_ROLE, UserRole> userDetailsService;
//	private final NVerseJWTService<LoomTenant, USER_ROLE, UserRole> jwtService;
//	private final AsterCronManager cron;
////	private final AuthorityTokenResponse response;
////	private final Auth0Service auth0;
////	private final LoomTenantDAOController daoController;
////	private final PasswordEncoder passwordEncoder;
//
////	@Autowired
//	public AuthenticationController(
//		RainTree rainTree,
//		AsterLogBook logBook,
//		AsterCronManager cron
////		NVerseGatekeeper<LoomTenant, USER_ROLE, UserRole> gatekeeper,
////		NVerseAuthorityResolver<LoomTenant, USER_ROLE, UserRole> authorityResolver,
////		HttpRequestDumpSanitizer httpRequestDumpSanitizer,
////		AuthorityTokenResponse response,
////		AuthenticationManager authenticationManager,
////		NVerseAuthenticationService nVerseService,
////		NVerseUserDetailsService<LoomTenant, USER_ROLE, UserRole> userDetailsService,
////		NVerseJWTService<LoomTenant, USER_ROLE, UserRole> jwtService,
////		Auth0Service auth0,
////		LoomTenantDAOController daoController,
////		PasswordEncoder passwordEncoder
//	) {
////		super(
////			rainTree,
////			logBook,
////			cron,
////			gatekeeper,
////			authorityResolver,
////			httpRequestDumpSanitizer
////		);
////		this.response = response;
////		this.authenticationManager = authenticationManager;
////		this.nVerseService = nVerseService;
////		this.userDetailsService = userDetailsService;
////		this.jwtService = jwtService;
//		this.cron = cron;
////		this.auth0 = auth0;
////		this.daoController = daoController;
////		this.passwordEncoder = passwordEncoder;
//	}
//
////	@Override
////	@PostConstruct
////	public void init() {
////		this.setClassName(
////			this.getClass().getName()
////		);
////	}
//
////	@PostMapping(
////		value = RequestMapper.EMAIL_AUTHENTICATION_URL,
////		consumes = Constant.REQUEST_TYPE_APPLICATION_JSON,
////		produces = Constant.RESPONSE_TYPE_APPLICATION_JSON
////	)
//	public ResponseEntity<?> createAuthenticationTokenUsingEmailID(
//		@RequestBody @NotNull NVerseRequest authRequest
//	) throws DisabledException, BadCredentialsException {
//
//		String username = authRequest.username();
//		String password = authRequest.password();
//
//		if (Pastebox.isEmptyString(username) || Pastebox.isEmptyString(password))
//			throw new AuthorizationException(AuthorizationException.AECx01);
//
//		// TODO: this is the reason userDetailsService.loadUser is called twice
////		this.nVerseService.authenticate(username, password, this.authenticationManager);
//
////		NVerseUser<LoomTenant, USER_ROLE, UserRole> user = this.userDetailsService.loadUserByUsername(username);
////		String token = this.jwtService.generateToken(user);
////
////		new Thread(() -> {
////			user.getTenant().setLastAccessTime(Pastebox.getCurrentTimeInMillis());
////			user.getTenant().setProvider(NVERSE_AUTH_PROVIDER.BASIC);
////			this.daoController.updateTenant(user.getTenant());
////		}).start();
//
////		this.cron.scheduleLoginLogTask(user.getTenant());
////
////		return ResponseEntity.ok(new NVerseResponse(token));
//
//		return null;
//	}
}
