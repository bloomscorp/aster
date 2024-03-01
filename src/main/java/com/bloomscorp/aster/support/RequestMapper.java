package com.bloomscorp.aster.support;

public final class RequestMapper {

	public static final String ROOT_URL = "/";
	public static final String ERROR = "/error";
	public static final String EMAIL_AUTHENTICATION_URL = "/authenticate/email";
	public static final String SOCIAL_AUTHENTICATION_URL = "/authenticate/social";
	public static final String GET_AUTHORITY_TOKEN = "/get/authority/token";
	public static final String VALIDATE_PROVIDER = "/validate/provider";
	public static final String POST_VERIFICATION_TOKEN = "/verification/token";
	public static final String SEND_VERIFICATION_EMAIL = "/send/verification/email";
	public static final String CONFIRM_VERIFICATION_EMAIL = "/confirm/verification/email";
	public static final String SEND_PASSWORD_RESET_EMAIL = "/send/password-reset/email";
	public static final String RESET_PASSWORD = "/reset/password";

	private RequestMapper() {}
}
