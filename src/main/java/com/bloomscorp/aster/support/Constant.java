package com.bloomscorp.aster.support;

public final class Constant {

	public static final String SCOPE_SINGLETON = "singleton";

	/*-- AUTH CONSTANTS --*/
	public static final int VERIFICATION_TOKEN_VALIDITY_IN_MINUTES = 1440; // 1 day
	public static final int CONFIRMATION_TOKEN_VALIDITY_IN_MINUTES = 120; // 2 hours

	/*--METADATA CONSTANTS--*/
	public static final String BLANK_STRING_VALUE = "";
	public static final String REQUEST_HEADER_AUTHORIZATION = "Authorization";
	public static final String REQUEST_HEADER_VALUE_BEARER = "Bearer ";
	public static final String NO_INFORMATION = "no information";
	public static final String REQUEST_TYPE_APPLICATION_JSON = "application/json";
	public static final String RESPONSE_TYPE_APPLICATION_JSON = "application/json";
	public static final String REQUEST_TYPE_MULTIPART_FORM_DATA = "multipart/form-data";

	/*--AWS--*/
	public static final String AWS_S3_ACL = "public-read";
	public static final double IMAGE_SIZE_LIMIT = 5e+6;

	/*--TIKA DETECTION TYPE CONSTANTS--*/
	public static final String TIKA_TYPE_IMAGE = "image";
	public static final String IMAGE_MEDIA_TYPE_JPG = "jpg";
	public static final String IMAGE_MEDIA_TYPE_JPEG = "jpeg";
	public static final String IMAGE_MEDIA_TYPE_PNG = "png";
	public static final String IMAGE_MEDIA_TYPE_SVG = "svg+xml";
	public static final String IMAGE_MEDIA_TYPE_WEBP = "webp";

	private Constant() {
	}
}
