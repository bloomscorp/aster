package com.bloomscorp.aster.support;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class LogMessage {

	/*--AUTH--*/
	public static final String TOKEN_VERIFIED = "token is verified";
	public static final String EMAIL_VERIFIED = "email is verified successfully";
	public static final String PASSWORD_RESET = "password reset successfully";
	public static final String VERIFICATION_EMAIL_SENT = "verification email sent successfully";
	public static final String PASSWORD_RESET_EMAIL_SENT = "password reset email sent successfully";

	/*--TENANT--*/
	public static final String UNAUTH_AUTH_TOKEN_REQUEST = buildUnAuthAccessLog("authority token");
	public static final String UNAUTH_PROFILE_REQUEST = buildUnAuthAccessLog("profile");
	public static final String UNAUTH_CUSTOMER_LIST_REQUEST = buildUnAuthAccessLog("customer list");
	public static final String NEW_CUSTOMER_CREATED = buildNewCreatedAccessLog("customer");
	public static final String CUSTOMER_UPDATED = buildUpdateAccessLog("customer");
	public static final String UNAUTH_CUSTOMER_UPDATE_REQUEST = buildUnAuthUpdateLog("customer");
	public static final String TENANT_STATUS_REQUEST_PROCESSED = "Tenant status request processed!";
	public static final String WISHLIST_UPDATED = buildUpdateAccessLog("customer");
	public static final String UNAUTH_WISHLIST_UPDATE_REQUEST = buildUnAuthUpdateLog("wishlist");

	/*--ADDRESS--*/
	public static final String UNAUTH_ADDRESS_LIST_REQUEST = buildUnAuthListAccessLog("address");
	public static final String NEW_ADDRESS_CREATED = buildNewCreatedAccessLog("address");
	public static final String UNAUTH_ADDRESS_CREATE_REQUEST = buildUnAuthCreateLog("address");
	public static final String ADDRESS_UPDATED = buildUpdateAccessLog("address");
	public static final String UNAUTH_ADDRESS_UPDATE_REQUEST = buildUnAuthUpdateLog("address");
	public static final String ADDRESS_DELETED = buildDeleteAccessLog("address");
	public static final String UNAUTH_ADDRESS_DELETE_REQUEST = buildUnAuthDeleteLog("address");

	private LogMessage() {}

	@Contract(pure = true)
	public static @NotNull String buildUnAuthAccessLog(String keyword) {
		return "Unauthorized request to access " + keyword + ": detected and blocked";
	}

	@Contract(pure = true)
	public static @NotNull String buildNewCreatedAccessLog(String keyword) {
		return "A new " + keyword + " is created";
	}

	@Contract(pure = true)
	public static @NotNull String buildUnAuthListAccessLog(String keyword) {
		return "Unauthorized request to access " + keyword + " list: detected and blocked";
	}

	@Contract(pure = true)
	public static @NotNull String buildUnAuthCreateLog(String keyword) {
		return "Unauthorized request to create " + keyword + ": detected and blocked";
	}

	@Contract(pure = true)
	public static @NotNull String buildUpdateAccessLog(String keyword) {
		return "A " + keyword + " is updated";
	}

	@Contract(pure = true)
	public static @NotNull String buildUnAuthUpdateLog(String keyword) {
		return "Unauthorized request to update " + keyword + ": detected and blocked";
	}

	@Contract(pure = true)
	public static @NotNull String buildDeleteAccessLog(String keyword) {
		return "A " + keyword + " is deleted";
	}

	@Contract(pure = true)
	public static @NotNull String buildUnAuthDeleteLog(String keyword) {
		return "Unauthorized request to delete " + keyword + ": detected and blocked";
	}
}
