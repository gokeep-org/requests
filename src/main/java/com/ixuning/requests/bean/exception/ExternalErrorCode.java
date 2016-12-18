package com.ixuning.requests.bean.exception;

public class ExternalErrorCode {
    // default
    public static final String REFRESH_TOKEN_IS_NULL_OR_INVALID = "token is null or invalid";
    public static final String REQUEST_METHOD_ERROR = "request method is error";
    public static final String REQUEST_NO_RESPONSE = "request is not response";
    public static final String SERVER_ERROR = "server error";
    public static final String REFRESH_TOKEN_INVALID = "refresh token is invalid";
    public static final String UN_SUPPORT_ENCODING = "no support encoding in entity transformation";
    public static final String HTTP_RESPONSE_IS_NULL = "http response is null";
    public static final String CONNECTION_REFUSED = "connection refused";
    public static final String HTTP_CLIENT_CLOSE_EXCEPTION = "http client close is exception";
    public static final String REQUEST_OPTION_ERROR = "request option is error";
    public static final String RESPONSE_BAD_GATEWAY = "502 Bad Gateway";
    public static final String REQUEST_IS_BAD = "request is bad";
    public static final String INVALID_ERROR = "invalid_error";
    public static final String EXTERNAL_LOGIN_PASSWORD_ERROR = "external_login_password_error";
    public static final String REQUEST_DATA_INVALID = "request_data_invalid";
    public static final String INVALID_TOKEN = "token is invalid";

    // permission denied
    public static final String PERMISSION_DENIED = "permission_denied";
    public static final String TOKEN_IS_NULL = "token is null";

    private String value;

    ExternalErrorCode(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
